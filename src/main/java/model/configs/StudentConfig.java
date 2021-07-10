package model.configs;

public class StudentConfig extends BaseConfig{
    private int nameSize = 0;
    private int lastNameSize = 0;

    // constructor
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
}
