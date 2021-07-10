package controller.adaptors;

import controller.file_stream.AppendableObjectOutputStream;

import java.io.IOException;

public abstract class FixRecordAdaptor extends Adaptor{
    public void writeRecord(AppendableObjectOutputStream oos, int value, int lastId) throws IOException {
        oos.write(lastId+1);
    }
    public int readRecord() {
        return 0;
    }
}
