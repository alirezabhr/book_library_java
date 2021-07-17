package views.settings_view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import constant.MyConst;
import views.widgets.CustomTextField;

public abstract class BaseSettingForm{

    // abstract methods
    public abstract VBox getForm();

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
        ToggleGroup recordRadioGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton("Fix");
        RadioButton radioButton2 = new RadioButton("Dynamic");

        radioButton1.setToggleGroup(recordRadioGroup);
        radioButton2.setToggleGroup(recordRadioGroup);

        return new VBox(recordModeLabel, radioButton1, radioButton2);
    }
    protected VBox getStringModeCol() {
        Label stringModeLabel = new Label("String Mode: ");
        ToggleGroup stringRadioGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton("Fix");
        RadioButton radioButton2 = new RadioButton("Dynamic");

        radioButton1.setToggleGroup(stringRadioGroup);
        radioButton2.setToggleGroup(stringRadioGroup);

        return new VBox(stringModeLabel, radioButton1, radioButton2);
    }
    protected VBox getRecordSizeCol() {
        Label recordSizeLabel = new Label("Record Size: ");
        CustomTextField recordSizeField = new CustomTextField("record size...");

        return new VBox(recordSizeLabel, recordSizeField);
    }
}
