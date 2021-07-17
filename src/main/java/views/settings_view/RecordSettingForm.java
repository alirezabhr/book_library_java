package views.settings_view;

import javafx.scene.layout.HBox;

public class RecordSettingForm extends BaseSettingForm {

    // constructors
    public RecordSettingForm() {
        this.formTitle = "<Record Config Settings>";
    }

    // methods
    protected HBox getMainRow() {
        return new HBox();
    }
}
