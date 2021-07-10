package controller.adaptors;

import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;

import java.io.IOException;

public abstract class Adaptor {
//    protected Config adpConf;
    protected String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // abstract methods
    public abstract void writeRecord(AppendableObjectOutputStream oos, int lastId) throws IOException;
    public abstract int readRecord(AppendableObjectInputStream ois) throws IOException;
    public abstract void writeStringField(AppendableObjectOutputStream oos, String value) throws IOException;
    public abstract String readStringField(AppendableObjectInputStream ois) throws IOException, ClassNotFoundException;

    // normal methods
    public void writeIntField(AppendableObjectOutputStream oos, int value) throws IOException {
        oos.writeInt(value);
    }
    public int readIntField(AppendableObjectInputStream ois) throws IOException {
        return ois.readInt();
    }
    public void editIntField() {

    }
    public void writeLongField(AppendableObjectOutputStream oos, long value) throws IOException {
        oos.writeLong(value);
    }
    public long readLongField(AppendableObjectInputStream ois) throws IOException {
        return ois.readLong();
    }
    public void editLongField() {

    }
}
