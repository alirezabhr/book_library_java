package controller.configs;

import constant.MyConst;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LibraryRecordConfig extends BaseConfig{

    // constructor
    public LibraryRecordConfig() {}
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
    public boolean isValidRecord(ArrayList<Object> objects) {
        if (objects.size() != 4) {
            return false;
        }

        final int sizeofInt = Integer.BYTES;
        int recordSize = sizeofInt*4;

        if (this.recordMode.equals("Fix")) {
            if (recordSize > this.recordSize) {
                return false;
            }
        }

        return true;
    }
    public boolean isValidFields(ArrayList<Object> objects) {
        if (objects.size() != 4) {
            return false;
        }

        return true;
    }
    public void write() throws IOException {
        FileWriter myWriter = new FileWriter(MyConst.constRecordConfigFilePathName);
        myWriter.write("\nRECORD_MODE = " + this.recordMode);
        myWriter.write("\nSTRING_MODE = " + this.stringMode);
        myWriter.close();
    }
}
