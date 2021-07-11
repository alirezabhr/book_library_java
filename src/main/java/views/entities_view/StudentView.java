package views.entities_view;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import model.entities.Student;
import controller.binders.StudentBinder;

import java.util.ArrayList;

public class StudentView extends EntityView {

    @Override
    protected HBox getSearchRow() {
        TextField nameSearchField = new TextField("name...");
        nameSearchField.setPrefWidth(95);
        nameSearchField.setOnMouseClicked(event->{
            nameSearchField.clear();
        });
        TextField lastNameSearchField = new TextField("last name...");
        lastNameSearchField.setPrefWidth(95);
        lastNameSearchField.setOnMouseClicked(event->{
            lastNameSearchField.clear();
        });
        TextField stdIdSearchField = new TextField("student id...");
        stdIdSearchField.setPrefWidth(95);
        stdIdSearchField.setOnMouseClicked(event->{
            stdIdSearchField.clear();
        });

        Image searchIcon = new Image("file:./images/search.png");
        Button searchBtn = new Button("Search", new ImageView(searchIcon));
        searchBtn.setCursor(Cursor.HAND);
        searchBtn.setOnAction(event -> {
            System.out.println("Button Clicked!!");
        });

        HBox searchRow = new HBox(nameSearchField, lastNameSearchField, stdIdSearchField, searchBtn);
        searchRow.setPadding(new Insets(10,20,10,32));
        searchRow.setSpacing(5);

        return searchRow;
    }

    @Override
    protected TableView getTable() {
        TableView table = createEmptyTable();
        ArrayList<Student> students = StudentBinder.getAllStudents();

        for (Student student:students) {
            table.getItems().add(student);
        }

        return table;
    }

    @Override
    protected TableView createEmptyTable() {
        TableView table = new TableView();

        TableColumn<Student, Integer> column0 = new TableColumn<>("Id");
        column0.setCellValueFactory(new PropertyValueFactory<>("uniqueId"));
        column0.setPrefWidth(30);

        TableColumn<Student, String> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column1.setPrefWidth(100);

        TableColumn<Student, String> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column2.setPrefWidth(100);

        TableColumn<Student, Integer> column3 = new TableColumn<>("Student Id");
        column3.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        column3.setPrefWidth(100);

        table.getColumns().add(column0);
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);

        table.setPrefHeight(1000);
        return table;
    }
}

