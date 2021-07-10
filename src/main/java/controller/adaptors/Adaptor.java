package controller.adaptors;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Adaptor {
//    protected Config adpConf;
    protected String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void writeIntField(ObjectOutputStream oos, int value) throws IOException {
        oos.writeInt(value);
    }
    public int readIntField(ObjectInputStream ois) throws IOException {
        return ois.readInt();
    }
    public void editIntField() {

    }
    public abstract void writeRecord();
    public abstract int readRecord();
}
