package views.entities_view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.util.Duration;
import views.widgets.ButtonCreator;
import views.widgets.ImageButton;
import views.widgets.MessageLabel;
import views.widgets.TitleLabel;

public abstract class EntityView {
    protected TableView table;
    protected TableView.TableViewSelectionModel selectionModel;
    protected MessageLabel msgLabel = new MessageLabel("", "red");

    // abstract methods
    protected abstract HBox getSearchRow();
    protected abstract TableView getTable();
    protected abstract TableView createEmptyTable();
    protected abstract void showCreateObjectForm();
    protected abstract void showEditObjectForm();
    protected abstract void deleteObjectRow();
    protected abstract void filterTable(String... filterParams);
    protected abstract void refreshTable();
    protected abstract void showSettingsForm();

    // normal methods
    public Tab getTab(String tabName) {
        VBox basePage = this.getForm(tabName + " Page");
        return new Tab(tabName, basePage);
    }

    protected VBox getForm(String formName) {
        HBox topRow = this.getTopRow(formName);
        HBox searchRow = this.getSearchRow();
        this.table = this.getTable();
        this.selectionModel = this.table.getSelectionModel();
        this.selectionModel.setSelectionMode(SelectionMode.SINGLE);

        return new VBox(topRow, searchRow, this.table);
    }

    protected HBox getTopRow(String formName) {
        TitleLabel formNameLabel = new TitleLabel(formName);

        ImageButton createBtn = ButtonCreator.getCreateButton();
        createBtn.setOnAction(event -> this.showCreateObjectForm());

        ImageButton editBtn = ButtonCreator.getEditButton();
        editBtn.setOnAction(event -> this.showEditObjectForm());

        ImageButton deleteBtn = ButtonCreator.getDeleteButton();
        deleteBtn.setOnAction(event -> this.deleteObjectRow());

        ImageButton refreshBtn = ButtonCreator.getRefreshButton();
        refreshBtn.setOnAction(event -> this.refreshTable());

        ImageButton settingBtn = ButtonCreator.getSettingsButton();
        settingBtn.setOnAction(event -> this.showSettingsForm());

        HBox hBox = new HBox(this.msgLabel);
        hBox.setPadding(new Insets(0, 0, 0, 250));

        HBox topRow = new HBox(formNameLabel, createBtn, editBtn, deleteBtn, refreshBtn, settingBtn, hBox);
        topRow.setPadding(new Insets(10));
        topRow.setSpacing(20);

        return topRow;
    }

    protected void showErrorMsg(String error) {
        this.msgLabel.setText(error);
        new Timeline(new KeyFrame(
                Duration.millis(2500),
                event -> this.msgLabel.setText("")))
                .play();
    }

    protected void showSuccessMsg(String msg) {
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

    protected void clearTable() {
        if (this.table.getItems().size() > 0) {
            this.table.getItems().subList(0, this.table.getItems().size()).clear();
        }
    }
}
