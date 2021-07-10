package controller.adaptors;

import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;

import java.io.IOException;

public abstract class DynamicRecordAdaptor extends Adaptor{
    public void writeRecord(AppendableObjectOutputStream oos, int lastId) throws IOException {
        oos.write(lastId+1);
    }
    public int readRecord(AppendableObjectInputStream ois) throws IOException {
        return ois.readInt();
    }
}
