package views.entities_view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import constant.MyConst;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import views.widgets.MessageLabel;
import views.widgets.TitleLabel;

public abstract class EntityView {
    protected TableView.TableViewSelectionModel selectionModel;
    protected MessageLabel msgLabel = new MessageLabel("", "red");

    // abstract methods
    protected abstract HBox getSearchRow();

    protected abstract TableView getTable();

    protected abstract TableView createEmptyTable();

    protected abstract void showCreateObjectForm();

    protected abstract void showEditObjectForm();

    protected abstract void deleteObjectRow();

    // normal methods
    public Tab getTab(String tabName) {
        VBox basePage = this.getForm(tabName + " Page");
        return new Tab(tabName, basePage);
    }

    protected VBox getForm(String formName) {
        HBox topRow = this.getTopRow(formName);
        HBox searchRow = this.getSearchRow();
        TableView table = this.getTable();
        TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        this.selectionModel = selectionModel;

        return new VBox(topRow, searchRow, table);
    }

    protected HBox getTopRow(String formName) {
        TitleLabel formNameLabel = new TitleLabel(formName);

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
        deleteBtn.setOnAction(event -> this.deleteObjectRow());
        deleteBtn.setMinWidth(60);

        HBox hBox = new HBox(this.msgLabel);
        hBox.setPadding(new Insets(0, 0, 0, 300));

        HBox topRow = new HBox(formNameLabel, createBtn, editBtn, deleteBtn, hBox);
        topRow.setPadding(new Insets(10));
        topRow.setSpacing(20);

        return topRow;
    }

    protected void changeErrorMsg(String error) {
        this.msgLabel.setText(error);
        new Timeline(new KeyFrame(
                Duration.millis(2500),
                event -> this.msgLabel.setText("")))
                .play();
    }

    protected void showSuccessfulDelete(String msg) {
        this.msgLabel.setTextFill(Color.valueOf("green"));
        this.msgLabel.setText(msg);
        new Timeline(new KeyFrame(
                Duration.millis(3500),
                event -> {
                    this.msgLabel.setText("");
                    this.msgLabel.setTextFill(Color.valueOf("red"));
                }))
                .play();
    }
}
