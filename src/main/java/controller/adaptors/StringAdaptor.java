package controller.adaptors;

import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;
import model.entities.Entity;

import java.io.IOException;

public interface StringAdaptor {
    boolean isValidFields(Entity entity);
    void writeStringField(AppendableObjectOutputStream oos, String value) throws IOException;
    String readStringField(AppendableObjectInputStream ois) throws IOException, ClassNotFoundException;
}
