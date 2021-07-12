package controller.adaptors;

import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;
import model.entities.Entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class Adaptor {

    // abstract methods
    public abstract boolean isValidObject(Entity entity);
    public abstract boolean isValidRecord(Entity entity);
    public abstract boolean isValidFields(Entity entity);
    public abstract void writeStringField(AppendableObjectOutputStream oos, String value) throws IOException;
    public abstract String readStringField(AppendableObjectInputStream ois) throws IOException, ClassNotFoundException;

    // normal methods
    public void createEmptyFile(String filePathName) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePathName);
        AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos);
        oos.close();
    }
    public int objectCount(Entity entity) {
        int count = 0;
        try {
            FileInputStream fis = new FileInputStream(entity.getEntityFilePathAndName());
            AppendableObjectInputStream ois = new AppendableObjectInputStream(fis);

            while (true){
                this.readRecord(entity, ois);
                count++;
            }
//            ois.close();
        } catch (Exception e) {
            return count;
        }
    }
    public void writeRecord(Entity entity) throws Exception {
        if (!this.isValidObject(entity)) {
            throw new Exception("Exception in write Record.\nNot A Valid Object");
        }
        int lastObjectId = this.objectCount(entity);

        FileOutputStream fos = new FileOutputStream(entity.getEntityFilePathAndName(), true);
        AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos);
        this.writeIntField(oos, lastObjectId+1);
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
    public Entity readRecord(Entity entity, AppendableObjectInputStream ois) throws IOException, ClassNotFoundException {
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

        entity.setEntityFieldsValue(fieldObjects);
        return entity;
    }
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
