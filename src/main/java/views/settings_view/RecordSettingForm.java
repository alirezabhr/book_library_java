package views.settings_view;

import controller.configs.ConfigCreator;
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
    protected void checkMainRowValidation() {}
    protected void setConfigs(String recMode, String strMode, String recSize) throws Exception {
        this.createNewConfig(recMode, strMode, recSize);
    }
    protected void createNewConfig(String recordMode, String stringMode, String... fields) throws Exception {
        Integer recordSize = Integer.parseInt(fields[0]);
        ConfigCreator.setRecordConfigFile(recordMode, stringMode, recordSize);
    }
}
