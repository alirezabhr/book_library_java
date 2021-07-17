package controller.configs;

import constant.MyConst;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentConfig extends BaseConfig{
    private int nameSize = 0;
    private int lastNameSize = 0;

    // constructor
    public StudentConfig() {}
    public StudentConfig(String fileName) {
        this.configFileName = fileName;
        try {
            this.createConfig();
        } catch (Exception exception) {
            System.out.println("Exception: Student Config");
            exception.printStackTrace();
        }
    }

    // getters
    public int getNameSize() {
        return nameSize;
    }
    public int getLastNameSize() {
        return lastNameSize;
    }

    // setters
    public void setNameSize(int nameSize) {
        this.nameSize = nameSize;
    }
    public void setLastNameSize(int lastNameSize) {
        this.lastNameSize = lastNameSize;
    }

    // methods
    protected void setFields(final String field, final String value) throws Exception {
        if (field.equals("RECORD_MODE") || field.equals("STRING_MODE") || field.equals("RECORD_SIZE")) {
            this.setBaseFields(field, value);
        } else if (field.equals("STUDENT_NAME_SIZE")) {
            if (this.isIntNumValid(value)) {
                this.nameSize = Integer.parseInt(value);
            } else {
                throw new Exception("Student Name Size is not valid");
            }
        } else if (field.equals("STUDENT_LAST_NAME_SIZE")) {
            if (this.isIntNumValid(value)) {
                this.lastNameSize = Integer.parseInt(value);
            } else {
                throw new Exception("Student Last Name Size is not valid");
            }
        } else {
            System.out.println("Student Config value or key is not valid");
            throw new Exception("Invalid Student Config");
        }
    }
    public boolean isValidRecord(ArrayList<Object> objects) {
        if (objects.size() != 3) {
            return false;
        }

        final int sizeofStudentId = Integer.BYTES;
        int nameSize = ((String)objects.get(1)).length();
        int lastNameSize = ((String)objects.get(2)).length();
        int recordSize = sizeofStudentId + nameSize + lastNameSize;

        if (this.recordMode.equals("Fix")) {
            if (recordSize > this.recordSize) {
                return false;
            }
        }

        return true;
    }
    public boolean isValidFields(ArrayList<Object> objects) {
        if (objects.size() != 3) {
            return false;
        }

        int nameSize = ((String)objects.get(1)).length();
        int lastNameSize = ((String)objects.get(2)).length();

        if (this.stringMode.equals("Fix")) {
            if (nameSize > this.nameSize || lastNameSize > this.lastNameSize) {
                return false;
            }
        }

        return true;
    }
    public void write() throws IOException {
        FileWriter myWriter = new FileWriter(MyConst.constStudentConfigFilePathName);
        myWriter.write("\nRECORD_MODE = " + this.recordMode);
        myWriter.write("\nSTRING_MODE = " + this.stringMode);
        myWriter.write("\nSTUDENT_NAME_SIZE = " + this.nameSize);
        myWriter.write("\nSTUDENT_LAST_NAME_SIZE = " + this.lastNameSize);
        myWriter.close();
    }
}
