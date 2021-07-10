package model.configs;

public class StudentConfig extends BaseConfig{
    private int studentNameSize = 0;
    private int studentLastNameSize = 0;

    // getters
    public int getStudentNameSize() {
        return studentNameSize;
    }
    public int getStudentLastNameSize() {
        return studentLastNameSize;
    }

    // methods
    protected void setFields(final String field, final String value) {

    }
}
