package controller.adaptors;

import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;
import model.entities.Entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class DynamicRecordAdaptor extends Adaptor{
    public void writeRecord(Entity entity) throws IOException {
        FileOutputStream fos = new FileOutputStream(entity.getEntityFilePathAndName(), true);
        AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos);
        this.writeIntField(oos, 2);     //todo write last id+1
        int counter = 0;
        ArrayList<Object> fieldObjects = entity.getAllFields();

        for (Type type : entity.getFieldsType()) {
            if (type.equals(Integer.class)) {
                this.writeIntField(oos, (Integer)fieldObjects.get(counter++));
            } else if (type.equals(String.class)) {
                this.writeStringField(oos, (String)fieldObjects.get(counter++));
            } else if (type.equals(Long.class)) {
                this.writeLongField(oos, (Long)fieldObjects.get(counter++));
            } else {
                System.out.println("Wrong Field Type");
            }
        }
        oos.close();
    }
    public Entity readRecord(Entity entity) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(entity.getEntityFilePathAndName());
        AppendableObjectInputStream ois = new AppendableObjectInputStream(fis);
        int uniqueId = this.readIntField(ois);
        entity.setUniqueId(uniqueId);
        ArrayList<Object> fieldObjects = new ArrayList<>();

        for (Type type : entity.getFieldsType()) {
            if (type.equals(Integer.class)) {
                fieldObjects.add(this.readIntField(ois));
            } else if (type.equals(String.class)) {
                fieldObjects.add(this.readStringField(ois));
            } else if (type.equals(Long.class)) {
                fieldObjects.add(this.readLongField(ois));
            } else {
                System.out.println("Wrong Field Type");
            }
        }
        ois.close();

        entity.setEntityFieldsValue(fieldObjects);
        return entity;
    }
}
