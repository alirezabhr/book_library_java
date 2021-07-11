package views.entities_view;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class EntityView {

    // abstract methods
    protected abstract HBox getSearchRow();
    protected abstract TableView getTable();
    protected abstract TableView createEmptyTable();

    // normal methods
    public Tab getTab(String tabName) {
        VBox studentForm = this.getForm();
        return new Tab(tabName, studentForm);
    }
    protected VBox getForm() {
        HBox topRow = this.getTopRow();
        HBox searchRow = this.getSearchRow();
        TableView table = this.getTable();

        return new VBox(topRow, searchRow, table);
    }
    protected static HBox getTopRow() {
        Label formNameLabel = new Label("Student Form");

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
}
