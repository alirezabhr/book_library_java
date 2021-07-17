package views.settings_view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import constant.MyConst;
import views.widgets.ButtonCreator;
import views.widgets.CustomTextField;
import views.widgets.ImageButton;
import views.widgets.TitleLabel;

public abstract class BaseSettingForm{
    protected String formTitle;
    protected CustomTextField recordSizeField;
    protected ToggleGroup recordModeGroup;
    protected ToggleGroup stringModeGroup;

    // abstract methods
    protected abstract HBox getMainRow();

    // methods
    public void show() {
        Scene scene = this.getScene();
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Settings");
        stage.getIcons().add(new Image(MyConst.constAppIconPathName));
        stage.show();

    }
    protected Scene getScene() {
        VBox root = this.getForm();
        return new Scene(root, 700, 450);
    }
    protected VBox getForm() {
        TitleLabel titleLabel = new TitleLabel(this.formTitle);

        HBox topRow = this.getTopRow();
        HBox mainRow = this.getMainRow();

        Separator separator1 = new Separator(Orientation.HORIZONTAL);
        Separator separator2 = new Separator(Orientation.HORIZONTAL);

        ImageButton saveButton = ButtonCreator.getSaveButton();
        HBox saveRow = new HBox(saveButton);
        saveRow.setPadding(new Insets(25));
        saveRow.setAlignment(Pos.BASELINE_CENTER);

        VBox form = new VBox(titleLabel, topRow, separator1, mainRow, separator2, saveRow);
        form.setAlignment(Pos.TOP_CENTER);
        return form;
    }
    protected HBox getTopRow() {
        VBox recordModeCol = this.getRecordModeCol();
        VBox stringModeCol = this.getStringModeCol();
        VBox recordSizeCol = this.getRecordSizeCol();

        recordModeCol.setSpacing(5);
        stringModeCol.setSpacing(5);
        recordSizeCol.setSpacing(5);

        HBox topRow = new HBox(recordModeCol, stringModeCol, recordSizeCol);
        topRow.setSpacing(80);
        topRow.setPadding(new Insets(20));
        return topRow;
    }
    protected VBox getRecordModeCol() {
        Label recordModeLabel = new Label("Record Mode: ");
        this.recordModeGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton("Fix");
        RadioButton radioButton2 = new RadioButton("Dynamic");

        radioButton1.setToggleGroup(this.recordModeGroup);
        radioButton2.setToggleGroup(this.recordModeGroup);

        return new VBox(recordModeLabel, radioButton1, radioButton2);
    }
    protected VBox getStringModeCol() {
        Label stringModeLabel = new Label("String Mode: ");
        this.stringModeGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton("Fix");
        RadioButton radioButton2 = new RadioButton("Dynamic");

        radioButton1.setToggleGroup(this.stringModeGroup);
        radioButton2.setToggleGroup(this.stringModeGroup);

        return new VBox(stringModeLabel, radioButton1, radioButton2);
    }
    protected VBox getRecordSizeCol() {
        Label recordSizeLabel = new Label("Record Size: ");
        this.recordSizeField = new CustomTextField("record size...");

        return new VBox(recordSizeLabel, this.recordSizeField);
    }
}
