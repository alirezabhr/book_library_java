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

import model.entities.Record;
import controller.binders.RecordBinder;
import constant.MyConst;
import views.entities_view.forms.RecordForm;

public class RecordView extends EntityView{

    @Override
    protected HBox getSearchRow() {
        TextField studentIdSearchField = new TextField();
        studentIdSearchField.setPromptText("student id...");
        studentIdSearchField.setPrefWidth(95);
        studentIdSearchField.setOnMouseClicked(event->{
            studentIdSearchField.clear();
        });
        TextField bookIdSearchField = new TextField();
        bookIdSearchField.setPromptText("book id...");
        bookIdSearchField.setPrefWidth(95);
        bookIdSearchField.setOnMouseClicked(event->{
            bookIdSearchField.clear();
        });
        TextField loanDateSearchField = new TextField();
        loanDateSearchField.setPromptText("loan date...");
        loanDateSearchField.setPrefWidth(95);
        loanDateSearchField.setOnMouseClicked(event->{
            loanDateSearchField.clear();
        });
        TextField returnDateSearchField = new TextField();
        returnDateSearchField.setPromptText("return date...");
        returnDateSearchField.setPrefWidth(95);
        returnDateSearchField.setOnMouseClicked(event->{
            returnDateSearchField.clear();
        });

        Image searchIcon = new Image(MyConst.constSearchImagePathName);
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
        ArrayList<Record> records = RecordBinder.getAllRecords();

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
        column4.setCellValueFactory(new PropertyValueFactory<>("intReturnDate"));
        column4.setPrefWidth(100);

        table.getColumns().setAll(column0, column1, column2, column3, column4);

        table.setPrefHeight(1000);
        return table;
    }
    @Override
    protected void showCreateObjectForm() {
        RecordForm recordForm = new RecordForm();
        recordForm.showForm("Create Record Form", recordForm.createFormDetail());
    }
    @Override
    protected void showEditObjectForm() {
        ObservableList<Record> selectedItems = this.selectionModel.getSelectedItems();
        if (selectedItems.size() == 0) {
            this.changeErrorMsg("Please Select A Row");
        } else {
            RecordForm recordForm = new RecordForm();
            recordForm.showForm("Edit Record Form", recordForm.editFormDetail(selectedItems.get(0)));
        }
    }
    @Override
    protected void showDeleteObjectForm() {}
}
