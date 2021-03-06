package views.entities_view;

import java.util.ArrayList;

import constant.MyConst;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import model.entities.Book;
import controller.binders.BookBinder;
import views.entities_view.forms.BookForm;
import views.settings_view.BookSettingForm;
import views.widgets.ButtonCreator;
import views.widgets.ImageButton;
import views.widgets.MessageLabel;
import views.widgets.SearchTextField;

public class BookView extends EntityView{

    @Override
    protected HBox getSearchRow() {
        SearchTextField nameSearchField = new SearchTextField("book name...");
        SearchTextField authorSearchField = new SearchTextField("author...");
        SearchTextField publisherSearchField = new SearchTextField("publisher...");
        SearchTextField isbnSearchField = new SearchTextField("isbn...");
        SearchTextField onLoanSearchField = new SearchTextField("onLoan...");

        ImageButton searchBtn = ButtonCreator.getSearchButton();
        searchBtn.setOnAction(event -> this.filterTable(nameSearchField.getText(), authorSearchField.getText(), publisherSearchField.getText(), isbnSearchField.getText(), onLoanSearchField.getText()));

        HBox searchRow = new HBox(nameSearchField, authorSearchField, publisherSearchField, isbnSearchField, onLoanSearchField, searchBtn);
        searchRow.setPadding(new Insets(10,20,10,32));
        searchRow.setSpacing(5);

        return searchRow;
    }
    @Override
    protected TableView getTable() {
        TableView table = createEmptyTable();
        Image emptyStateImage = new Image(MyConst.constEmptyStateImagePathName);
        VBox tablePlaceHolder = new VBox(new ImageView(emptyStateImage), new MessageLabel("No Book Found", "black"));
        tablePlaceHolder.setSpacing(20);
        tablePlaceHolder.setAlignment(Pos.CENTER);
        table.setPlaceholder(tablePlaceHolder);
        ArrayList<Book> books = BookBinder.getAllBooks();

        for (Book book:books) {
            table.getItems().add(book);
        }

        return table;
    }
    @Override
    protected TableView createEmptyTable() {
        TableView table = new TableView();

        TableColumn<Book, Integer> column0 = new TableColumn<>("Id");
        column0.setCellValueFactory(new PropertyValueFactory<>("uniqueId"));
        column0.setPrefWidth(30);

        TableColumn<Book, String> column1 = new TableColumn<>("Book Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column1.setPrefWidth(100);

        TableColumn<Book, String> column2 = new TableColumn<>("Author");
        column2.setCellValueFactory(new PropertyValueFactory<>("author"));
        column2.setPrefWidth(100);

        TableColumn<Book, String> column3 = new TableColumn<>("Publisher");
        column3.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        column3.setPrefWidth(100);

        TableColumn<Book, Long> column4 = new TableColumn<>("ISBN");
        column4.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        column4.setPrefWidth(100);

        TableColumn<Book, Integer> column5 = new TableColumn<>("On Loan");
        column5.setCellValueFactory(new PropertyValueFactory<>("onLoan"));
        column5.setPrefWidth(100);

        table.getColumns().setAll(column0, column1, column2, column3, column4, column5);

        table.setPrefHeight(1000);
        return table;
    }
    @Override
    protected void showCreateObjectForm() {
        BookForm bookForm = new BookForm();
        bookForm.showForm("Create Book Form", bookForm.createFormDetail());
    }
    @Override
    protected void showEditObjectForm() {
        ObservableList<Book> selectedItems = this.selectionModel.getSelectedItems();
        if (selectedItems.size() == 0) {
            this.showErrorMsg("Please Select A Row");
        } else {
            BookForm bookForm = new BookForm();
            bookForm.showForm("Edit Book Form", bookForm.editFormDetail(selectedItems.get(0)));
        }
    }
    @Override
    protected void deleteObjectRow() {
        ObservableList<Book> selectedItems = this.selectionModel.getSelectedItems();
        if (selectedItems.size() == 0) {
            this.showErrorMsg("Please Select A Row");
        } else {
            boolean isDeleted = BookBinder.deleteObject(selectedItems.get(0).getUniqueId());
            if (isDeleted){
                this.showSuccessMsg("Book Deleted Successfully");
                this.refreshTable();
            } else {
                this.showErrorMsg("Can Not Delete Book Right Now!");
            }
        }
    }
    @Override
    protected void filterTable(String... filterParams) {
        try {
            ArrayList<Book> filteredData = BookBinder.getFilteredData(filterParams);
            this.clearTable();
            for (Book book: filteredData) {
                this.table.getItems().add(book);
            }
        } catch (Exception exception) {
            this.showErrorMsg(exception.getMessage());
        }
    }
    @Override
    protected void refreshTable() {
        ArrayList<Book> filteredData = BookBinder.getAllBooks();
        this.clearTable();
        for (Book book: filteredData) {
            this.table.getItems().add(book);
        }
    }
    @Override
    protected void showSettingsForm() {
        BookSettingForm form = new BookSettingForm();
        form.show();
    }
}
