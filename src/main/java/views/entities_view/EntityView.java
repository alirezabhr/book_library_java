package views.entities_view;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import constant.MyConst;

public abstract class EntityView {

    // abstract methods
    protected abstract HBox getSearchRow();
    protected abstract TableView getTable();
    protected abstract TableView createEmptyTable();
    protected abstract void showCreateObjectForm();
    protected abstract void showEditObjectForm();
    protected abstract void showDeleteObjectForm();

    // normal methods
    public Tab getTab(String tabName) {
        VBox basePage = this.getForm(tabName+" Page");
        return new Tab(tabName, basePage);
    }
    protected VBox getForm(String formName) {
        HBox topRow = this.getTopRow(formName);
        HBox searchRow = this.getSearchRow();
        TableView table = this.getTable();

        return new VBox(topRow, searchRow, table);
    }
    protected HBox getTopRow(String formName) {
        Label formNameLabel = new Label(formName);

        Image createIcon = new Image(MyConst.constAddImagePathName);
        Image editIcon = new Image(MyConst.constEditImagePathName);
        Image deleteIcon = new Image(MyConst.constDeleteImagePathName);

        Button createBtn = new Button("Create");
        createBtn.setGraphic(new ImageView(createIcon));
        createBtn.setCursor(Cursor.HAND);
        createBtn.setOnAction(event -> this.showCreateObjectForm());
        createBtn.setMinWidth(60);

        Button editBtn = new Button("Edit");
        editBtn.setGraphic(new ImageView(editIcon));
        editBtn.setCursor(Cursor.HAND);
        editBtn.setOnAction(event -> this.showEditObjectForm());
        editBtn.setMinWidth(60);

        Button deleteBtn = new Button("Delete");
        deleteBtn.setGraphic(new ImageView(deleteIcon));
        deleteBtn.setCursor(Cursor.HAND);
        deleteBtn.setOnAction(event -> this.showDeleteObjectForm());
        deleteBtn.setMinWidth(60);

        HBox topRow = new HBox(formNameLabel, createBtn, editBtn, deleteBtn);
        topRow.setPadding(new Insets(10));
        topRow.setSpacing(20);

        return topRow;
    }
}
