package views.entities_view;

import java.util.ArrayList;

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

public class BookView extends EntityView{

    @Override
    protected HBox getSearchRow() {
        TextField nameSearchField = new TextField("name...");
        nameSearchField.setPrefWidth(95);
        nameSearchField.setOnMouseClicked(event->{
            nameSearchField.clear();
        });
        TextField authorSearchField = new TextField("author...");
        authorSearchField.setPrefWidth(95);
        authorSearchField.setOnMouseClicked(event->{
            authorSearchField.clear();
        });
        TextField publisherSearchField = new TextField("publisher...");
        publisherSearchField.setPrefWidth(95);
        publisherSearchField.setOnMouseClicked(event->{
            publisherSearchField.clear();
        });
        TextField isbnSearchField = new TextField("isbn...");
        isbnSearchField.setPrefWidth(95);
        isbnSearchField.setOnMouseClicked(event->{
            isbnSearchField.clear();
        });
        TextField onLoanSearchField = new TextField("onLoan...");
        onLoanSearchField.setPrefWidth(95);
        onLoanSearchField.setOnMouseClicked(event->{
            onLoanSearchField.clear();
        });


        Image searchIcon = new Image(MyConst.constSearchImagePathName);
        Button searchBtn = new Button("Search", new ImageView(searchIcon));
        searchBtn.setCursor(Cursor.HAND);
        searchBtn.setOnAction(event -> {
            System.out.println("Button Clicked!!");
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
}
