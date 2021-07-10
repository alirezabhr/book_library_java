package model.configs;

public class LibraryRecordConfig extends BaseConfig{

    // constructor
    public LibraryRecordConfig(String fileName) {
        this.configFileName = fileName;
        try {
            this.createConfig();
        } catch (Exception exception) {
            System.out.println("Exception: Library Record Config");
            exception.printStackTrace();
        }
    }

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
