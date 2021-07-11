package controller.adaptors;

import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;
import model.entities.Entity;

import java.io.IOException;

public class DynRecFixStr extends DynamicRecordAdaptor implements StringAdaptor{
    @Override
    public boolean isValidObject(Entity entity) {
        return this.isValidFields(entity);
    }
    @Override
    public boolean isValidFields(Entity entity) {
        return entity.getBaseConfig().isValidFields(entity.getAllFields());
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
