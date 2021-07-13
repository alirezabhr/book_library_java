package views.entities_view;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import model.entities.Student;
import controller.binders.StudentBinder;
import views.entities_view.forms.StudentForm;
import views.widgets.ButtonCreator;
import views.widgets.ImageButton;
import views.widgets.SearchTextField;

public class StudentView extends EntityView {

    @Override
    protected HBox getSearchRow() {
        SearchTextField nameSearchField = new SearchTextField("name...");
        SearchTextField lastNameSearchField = new SearchTextField("last name...");
        SearchTextField stdIdSearchField = new SearchTextField("student id...");

        ImageButton searchBtn = ButtonCreator.getSearchButton();
        searchBtn.setOnAction(event -> this.filterTable(nameSearchField.getText(), lastNameSearchField.getText(), stdIdSearchField.getText()));

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

        table.getColumns().setAll(column0, column1, column2, column3);

        table.setPrefHeight(1000);
        return table;
    }
    @Override
    protected void showCreateObjectForm() {
        StudentForm studentForm = new StudentForm();
        studentForm.showForm("Create Student Form", studentForm.createFormDetail());
    }
    @Override
    protected void showEditObjectForm() {
        ObservableList<Student> selectedItems = this.selectionModel.getSelectedItems();
        if (selectedItems.size() == 0) {
            this.showErrorMsg("Please Select A Row");
        } else {
            StudentForm studentForm = new StudentForm();
            studentForm.showForm("Edit Student Form", studentForm.editFormDetail(selectedItems.get(0)));
        }
    }
    @Override
    protected void deleteObjectRow() {
        ObservableList<Student> selectedItems = this.selectionModel.getSelectedItems();
        if (selectedItems.size() == 0) {
            this.showErrorMsg("Please Select A Row");
        } else {
            boolean isDeleted = StudentBinder.deleteObject(selectedItems.get(0).getUniqueId());
            if (isDeleted){
                this.showSuccessMsg("Student Deleted Successfully");
                refreshTable();
            } else {
                this.showErrorMsg("Can Not Delete Student Right Now!");
            }
        }
    }
    @Override
    protected void filterTable(String... filterParams) {
        try {
            ArrayList<Student> filteredData = StudentBinder.getFilteredData(filterParams);
            this.clearTable();
            for (Student student: filteredData) {
                this.table.getItems().add(student);
            }
        } catch (Exception exception) {
            this.showErrorMsg(exception.getMessage());
        }
    }
    @Override
    protected void refreshTable() {
        ArrayList<Student> filteredData = StudentBinder.getAllStudents();
        this.clearTable();
        for (Student student: filteredData) {
            this.table.getItems().add(student);
        }
    }
}

