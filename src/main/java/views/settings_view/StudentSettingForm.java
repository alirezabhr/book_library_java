package views.settings_view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import views.widgets.ButtonCreator;
import views.widgets.ImageButton;

public class StudentSettingForm extends BaseSettingForm {

    // methods
    public VBox getForm() {
        HBox topRow = this.getTopRow();

        ImageButton saveButton = ButtonCreator.getSaveButton();
        return new VBox(topRow, saveButton);
    }
}
