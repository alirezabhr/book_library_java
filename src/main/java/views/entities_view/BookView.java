package views.entities_view;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import model.entities.Book;
import controller.binders.BookBinder;
import constant.MyConst;
import views.entities_view.forms.BookForm;
import views.widgets.ButtonCreator;
import views.widgets.ImageButton;
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
        searchBtn.setOnAction(event -> {
            this.filterTable();
        });

        HBox searchRow = new HBox(nameSearchField, authorSearchField, publisherSearchField, isbnSearchField, onLoanSearchField, searchBtn);
        searchRow.setPadding(new Insets(10,20,10,32));
        searchRow.setSpacing(5);

        return searchRow;
    }
    @Override
    protected TableView getTable() {
        TableView table = createEmptyTable();
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
            this.changeErrorMsg("Please Select A Row");
        } else {
            BookForm bookForm = new BookForm();
            bookForm.showForm("Edit Book Form", bookForm.editFormDetail(selectedItems.get(0)));
        }
    }
    @Override
    protected void deleteObjectRow() {
        ObservableList<Book> selectedItems = this.selectionModel.getSelectedItems();
        if (selectedItems.size() == 0) {
            this.changeErrorMsg("Please Select A Row");
        } else {
            boolean isDeleted = BookBinder.deleteObject(selectedItems.get(0).getUniqueId());
            if (isDeleted){
                this.showSuccessfulDelete("Book Deleted Successfully");
            } else {
                this.changeErrorMsg("Can Not Delete Book Right Now!");
            }
        }
    }

    protected void filterTable() {}
}
