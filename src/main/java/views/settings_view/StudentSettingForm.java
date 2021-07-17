package views.settings_view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
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
}
