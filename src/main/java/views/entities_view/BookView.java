package views.entities_view;

import controller.binders.BookBinder;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.entities.Book;

import java.util.ArrayList;

public class BookView {

    public static Tab getTab() {
        VBox bookForm = getForm();
        return new Tab("Books", bookForm);
    }

    private static VBox getForm() {
        HBox topRow = getTopRow();
        HBox searchRow = getSearchRow();
        TableView table = getTable();

        return new VBox(topRow, searchRow, table);
    }

    private static HBox getSearchRow() {
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


        Image searchIcon = new Image("file:./images/search.png");
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

    private static HBox getTopRow() {
        Label formNameLabel = new Label("Book Form");

        Image createIcon = new Image("file:./images/add.png");
        Image editIcon = new Image("file:./images/edit.png");
        Image deleteIcon = new Image("file:./images/delete.png");

        Button createBtn = new Button("Create");
        createBtn.setGraphic(new ImageView(createIcon));
        createBtn.setCursor(Cursor.HAND);
        createBtn.setMinWidth(60);

        Button editBtn = new Button("Edit");
        editBtn.setGraphic(new ImageView(editIcon));
        editBtn.setCursor(Cursor.HAND);
        editBtn.setMinWidth(60);

        Button deleteBtn = new Button("Delete");
        deleteBtn.setGraphic(new ImageView(deleteIcon));
        deleteBtn.setCursor(Cursor.HAND);
        deleteBtn.setMinWidth(60);

        HBox topRow = new HBox(formNameLabel, createBtn, editBtn, deleteBtn);
        topRow.setPadding(new Insets(10));
        topRow.setSpacing(20);

        return topRow;
    }

    private static TableView getTable() {
        TableView table = createEmptyTable();
        ArrayList<Book> books = BookBinder.getAllBooks();

        for (Book book:books) {
            table.getItems().add(book);
        }

        return table;
    }

    private static TableView createEmptyTable() {
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

        TableColumn<Book, Integer> column3 = new TableColumn<>("Publisher");
        column3.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        column3.setPrefWidth(100);

        TableColumn<Book, Long> column4 = new TableColumn<>("ISBN");
        column0.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        column0.setPrefWidth(100);

        TableColumn<Book, Integer> column5 = new TableColumn<>("On Loan");
        column0.setCellValueFactory(new PropertyValueFactory<>("onLoan"));
        column0.setPrefWidth(100);

        table.getColumns().add(column0);
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
        table.getColumns().add(column5);

        table.setPrefHeight(1000);
        return table;
    }
}
