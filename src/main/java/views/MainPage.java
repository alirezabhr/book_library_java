package views;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MainPage {
    private ArrayList<String> entityChoicesList = new ArrayList<>();

    private static class Person {
        private Integer id;
        private String firstName = null;
        private String lastName = null;
        private Integer stdId;

        private Person(int id, String firstName, String lastName, Integer stdId) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.stdId = stdId;
        }

        private Integer getUniqueId() {
            return id;
        }

        private String getFirstName() {
            return firstName;
        }

        private String getLastName() {
            return lastName;
        }

        private Integer getStdId() {
            return stdId;
        }
    }

    private static TableView getPersonTable() {
        TableView table = new TableView();

        TableColumn<Person, Integer> column0 = new TableColumn<>("Id");
        column0.setCellValueFactory(new PropertyValueFactory<>("uniqueId"));
        column0.setPrefWidth(30);

        TableColumn<Person, String> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column1.setPrefWidth(100);

        TableColumn<Person, String> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column2.setPrefWidth(100);

        TableColumn<Person, Integer> column3 = new TableColumn<>("Student Id");
        column3.setCellValueFactory(new PropertyValueFactory<>("stdId"));
        column3.setPrefWidth(100);

        table.getColumns().add(column0);
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);

        table.setPrefHeight(1000);
        return table;
    }

    public static Scene getMainScene() {
        ArrayList<Person> arr = new ArrayList<>();
        arr.add(new Person(1, "alireza", "bahrol", 9832089));
        arr.add(new Person(2, "hossein", "serati", 9832079));
        arr.add(new Person(3, "mamad", "mamadi", 9722053));
        arr.add(new Person(4, "reza", "rezaei", 8721365));
        arr.add(new Person(5, "shapour", "akbari", 6846546));
        arr.add(new Person(6, "abbas", "ebrahimi", 7541366));

        ArrayList<String> choicesList = new ArrayList<>();
        choicesList.add("Student");
        choicesList.add("Book");
        choicesList.add("Record");

        ChoiceBox<String> entityChoiceBox = new ChoiceBox<>();
        entityChoiceBox.setMinWidth(100);
        entityChoiceBox.setCursor(Cursor.HAND);
        for (String choice : choicesList) {
            entityChoiceBox.getItems().add(choice);
        }
        entityChoiceBox.setValue(choicesList.get(0));


        Button createBtn = new Button("Create");
        createBtn.setCursor(Cursor.HAND);
        createBtn.setMinWidth(60);
        Button editBtn = new Button("Edit");
        editBtn.setCursor(Cursor.HAND);
        editBtn.setMinWidth(60);
        Button deleteBtn = new Button("Delete");
        deleteBtn.setCursor(Cursor.HAND);
        deleteBtn.setMinWidth(60);

        HBox topRow = new HBox(entityChoiceBox, createBtn, editBtn, deleteBtn);
        topRow.setPadding(new Insets(10));
        topRow.setSpacing(20);

        Image searchIcon = new Image("file:./images/search.png");

        Button searchBtn = new Button("Search", new ImageView(searchIcon));
        searchBtn.setCursor(Cursor.HAND);
        searchBtn.setOnAction(event -> {
            System.out.println("Button Clicked!!");
        });

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

        HBox searchRow = new HBox(nameSearchField, lastNameSearchField, stdIdSearchField, searchBtn);
        searchRow.setPadding(new Insets(10,20,10,32));
        searchRow.setSpacing(5);

        TableView table = getPersonTable();

        for (Person person:arr) {
            table.getItems().add(person);
        }

        VBox root = new VBox(topRow, searchRow, table);

        return new Scene(root, 900, 550);
    }
}
