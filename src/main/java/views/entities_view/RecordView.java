package views.entities_view;

import controller.binders.RecordBinder;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.entities.Record;

import java.util.ArrayList;

public class RecordView extends EntityView{

    @Override
    protected HBox getSearchRow() {
        TextField studentIdSearchField = new TextField("student id...");
        studentIdSearchField.setPrefWidth(95);
        studentIdSearchField.setOnMouseClicked(event->{
            studentIdSearchField.clear();
        });
        TextField bookIdSearchField = new TextField("book id...");
        bookIdSearchField.setPrefWidth(95);
        bookIdSearchField.setOnMouseClicked(event->{
            bookIdSearchField.clear();
        });
        TextField loanDateSearchField = new TextField("loan date...");
        loanDateSearchField.setPrefWidth(95);
        loanDateSearchField.setOnMouseClicked(event->{
            loanDateSearchField.clear();
        });
        TextField returnDateSearchField = new TextField("return date...");
        returnDateSearchField.setPrefWidth(95);
        returnDateSearchField.setOnMouseClicked(event->{
            returnDateSearchField.clear();
        });

        Image searchIcon = new Image("file:./images/search.png");
        Button searchBtn = new Button("Search", new ImageView(searchIcon));
        searchBtn.setCursor(Cursor.HAND);
        searchBtn.setOnAction(event -> {
            System.out.println("Button Clicked!!");
        });

        HBox searchRow = new HBox(studentIdSearchField, bookIdSearchField, loanDateSearchField, returnDateSearchField, searchBtn);
        searchRow.setPadding(new Insets(10,20,10,32));
        searchRow.setSpacing(5);

        return searchRow;
    }

    @Override
    protected TableView getTable() {
        TableView table = createEmptyTable();
        ArrayList<Record> records = RecordBinder.getAllStudents();

        for (Record record:records) {
            table.getItems().add(record);
        }

        return table;
    }

    @Override
    protected TableView createEmptyTable() {
        TableView table = new TableView();

        TableColumn<Record, Integer> column0 = new TableColumn<>("Id");
        column0.setCellValueFactory(new PropertyValueFactory<>("uniqueId"));
        column0.setPrefWidth(30);

        TableColumn<Record, Integer> column1 = new TableColumn<>("Student Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        column1.setPrefWidth(100);

        TableColumn<Record, Integer> column2 = new TableColumn<>("Book Id");
        column2.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        column2.setPrefWidth(100);

        TableColumn<Record, Integer> column3 = new TableColumn<>("Loan Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("intLoanedDate"));
        column3.setPrefWidth(100);

        TableColumn<Record, Integer> column4 = new TableColumn<>("Return Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("intReturnDate"));
        column3.setPrefWidth(100);

        table.getColumns().add(column0);
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);

        table.setPrefHeight(1000);
        return table;
    }
}
