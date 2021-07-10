package controller.adaptors;

import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;

import java.io.IOException;

public interface DynamicStringAdaptor {
    void writeStringField(AppendableObjectOutputStream oos, String value) throws IOException;
    String readStringField(AppendableObjectInputStream ois) throws IOException, ClassNotFoundException;
}
