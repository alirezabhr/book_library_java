package views.settings_view;

import controller.utils;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import constant.MyConst;
import views.widgets.*;

public abstract class BaseSettingForm{
    protected String formTitle;
    protected CustomTextField recordSizeField;
    protected ToggleGroup recordModeGroup;
    protected ToggleGroup stringModeGroup;

    // abstract methods
    protected abstract HBox getMainRow();
    protected abstract void checkMainRowValidation() throws Exception;
    protected abstract void setConfigs(String recMode, String strMode, String recSize) throws Exception;
    protected abstract void createNewConfig(String recordMode, String stringMode, String... fields) throws Exception;

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
        MessageLabel msgLabel = new MessageLabel("", "green");
        saveButton.setOnAction(event -> {
            String msg = this.save();
            if (msg.equals("saved")) {
                msgLabel.setText("Settings Saved Successfully.");
                msgLabel.setTextFill(Color.valueOf("green"));
            } else {
                msgLabel.setText(msg);
                msgLabel.setTextFill(Color.valueOf("red"));
            }
        });


        VBox saveCol = new VBox(saveButton, msgLabel);
        saveCol.setPadding(new Insets(20));
        saveCol.setSpacing(10);
        saveCol.setAlignment(Pos.BASELINE_CENTER);

        VBox form = new VBox(titleLabel, topRow, separator1, mainRow, separator2, saveCol);
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
    protected String save() {
        try {
            this.isValidSettings();
        } catch (Exception exception) {
            return exception.getMessage();
        }

        try {
            RadioButton recModeRadioButton = (RadioButton) this.recordModeGroup.getSelectedToggle();
            RadioButton strModeRadioButton = (RadioButton) this.stringModeGroup.getSelectedToggle();
            String recordMode = recModeRadioButton.getText();
            String stringMode = strModeRadioButton.getText();
            String recordSizeStr = this.recordSizeField.getText();
            this.setConfigs(recordMode, stringMode, recordSizeStr);
        } catch (Exception exception) {
            System.out.println("ajibe");
        }

        return "saved";
    }
    protected void isValidSettings() throws Exception {
        this.checkTopRowValidation();
        this.checkMainRowValidation();
    }
    protected void checkTopRowValidation() throws Exception {
        if (this.recordModeGroup.getSelectedToggle()==null) {
            throw new Exception("please select a record mode!");
        }
        if (this.stringModeGroup.getSelectedToggle()==null) {
            throw new Exception("please select a string mode!");
        }
        RadioButton selectedRecMode = (RadioButton) this.recordModeGroup.getSelectedToggle();
        if (selectedRecMode.getText().equals("Fix")) {
            boolean isIntRecSize = utils.isIntNumber(this.recordSizeField.getText());
            if (!isIntRecSize) {
                throw new Exception("record size should be an integer number");
            }
        }
    }
}
