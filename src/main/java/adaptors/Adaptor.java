package adaptors;

public abstract class Adaptor {
//    protected Config adpConf;
    protected String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void writeIntField() {

    }
    public int readIntField() {
        return 0;
    }
    public void editIntField() {

    }
    public abstract void writeRecord();
    public abstract int readRecord();
}
