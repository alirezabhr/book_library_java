package views.settings_view;

import controller.configs.ConfigCreator;
import controller.utils;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import views.widgets.CustomTextField;

public class StudentSettingForm extends BaseSettingForm {
    protected CustomTextField nameSizeField;
    protected CustomTextField lastNameSizeField;

    // constructors
    public StudentSettingForm() {
        this.formTitle = "<Student Config Settings>";
    }

    // methods
    protected HBox getMainRow() {
        VBox nameCol = this.getNameCol();
        VBox lastNameCol = this.getLastNameCol();

        HBox mainRow = new HBox(nameCol, lastNameCol);
        mainRow.setSpacing(80);
        mainRow.setPadding(new Insets(20));
        return mainRow;
    }
    private VBox getNameCol() {
        Label nameLabel = new Label("Student Name Size: ");
        this.nameSizeField = new CustomTextField("name size...");

        VBox nameCol = new VBox(nameLabel, this.nameSizeField);
        nameCol.setSpacing(5);
        return nameCol;
    }
    private VBox getLastNameCol() {
        Label lastNameLabel = new Label("Student Last Name Size: ");
        this.lastNameSizeField = new CustomTextField("last name size...");

        VBox lastNameCol = new VBox(lastNameLabel, this.lastNameSizeField);
        lastNameCol.setSpacing(5);
        return lastNameCol;
    }
    protected void checkMainRowValidation() throws Exception {
        RadioButton selectedStrMode = (RadioButton) this.stringModeGroup.getSelectedToggle();
        if (selectedStrMode.getText().equals("Fix")) {
            boolean isIntNameSize = utils.isIntNumber(this.nameSizeField.getText());
            if (!isIntNameSize) {
                throw new Exception("name size should be an integer number");
            }
            boolean isIntLastNameSize = utils.isIntNumber(this.lastNameSizeField.getText());
            if (!isIntLastNameSize) {
                throw new Exception("last name size should be an integer number");
            }
        }
    }
    protected void setConfigs(String recMode, String strMode, String recSize) throws Exception {
        this.createNewConfig(recMode, strMode, recSize, nameSizeField.getText(), lastNameSizeField.getText());
    }
    protected void createNewConfig(String recordMode, String stringMode, String... fields) throws Exception {
        Integer recordSize = Integer.parseInt(fields[0]);
        Integer nameSize = Integer.parseInt(fields[1]);
        Integer lastNameSize = Integer.parseInt(fields[2]);

        ConfigCreator.setStudentConfigFile(recordMode, stringMode, recordSize, nameSize, lastNameSize);
    }
}
