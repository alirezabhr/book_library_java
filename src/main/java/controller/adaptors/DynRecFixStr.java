package controller.adaptors;

import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;

import java.io.IOException;

public class DynRecFixStr extends DynamicRecordAdaptor implements FixStringAdaptor{
    public void writeStringField(AppendableObjectOutputStream oos, String value) throws IOException {
        oos.writeObject(value);
    }
    public String readStringField(AppendableObjectInputStream ois) throws IOException, ClassNotFoundException {
        return (String) ois.readObject();
    }
}
