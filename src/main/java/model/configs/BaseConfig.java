package model.configs;

public abstract class BaseConfig {
    protected String recordMode;
    protected String stringMode;
    protected int recordSize = 0;

    // getters
    public String getRecordMode() {
        return recordMode;
    }
    public String getStringMode() {
        return stringMode;
    }
    public int getRecordSize() {
        return recordSize;
    }

    // abstract methods
//    public abstract boolean isFixFixValid();  //todo check if we need this method
//    public abstract void readConfigFile();
    protected abstract void setFields(final String field, final String value);

    // normal methods
    protected String[] tokenize(String line) {
        return line.split(" = ");
    }
}
