package model.configs;

public class LibraryRecordConfig extends BaseConfig{

    // methods
    protected void setFields(final String field, final String value) throws Exception {
        if (field.equals("RECORD_MODE") || field.equals("STRING_MODE") || field.equals("RECORD_SIZE")) {
            this.setBaseFields(field, value);
        } else {
            System.out.println("Library Record Config value or key is not valid");
            throw new Exception("Invalid Library Record Config");
        }
    }
}
