package controller.adaptors;

import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;
import model.entities.Entity;

import java.io.IOException;

public abstract class Adaptor {

    // abstract methods
    public abstract void writeRecord(Entity entity) throws IOException;
    public abstract Entity readRecord(Entity entity) throws IOException, ClassNotFoundException;
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
