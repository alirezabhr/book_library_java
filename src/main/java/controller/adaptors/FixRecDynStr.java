package controller.adaptors;

import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;
import model.entities.Entity;

import java.io.IOException;

public class FixRecDynStr extends FixRecordAdaptor implements StringAdaptor{
    @Override
    public boolean isValidObject(Entity entity) {
        return this.isValidRecord(entity);
    }
    @Override
    public boolean isValidFields(Entity entity) {
        return true;
    }
    @Override
    public void writeStringField(AppendableObjectOutputStream oos, String value) throws IOException {
        oos.writeObject(value);
    }
    @Override
    public String readStringField(AppendableObjectInputStream ois) throws IOException, ClassNotFoundException {
        return (String) ois.readObject();
    }
}
