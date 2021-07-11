package model.entities;

import controller.adaptors.Adaptor;
import controller.configs.BaseConfig;
import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;

import javax.management.ObjectName;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class Entity {
    protected Adaptor adaptor;
    protected BaseConfig baseConfig;
    protected int uniqueId;
    protected final String constBaseFilePath = "./database/";
    protected String entityFilePathAndName;
    protected ArrayList<String> fieldsName;
    protected ArrayList<Type> fieldsType;
    protected ArrayList<Object> fields;

    // getters
    public String getEntityFilePathAndName() {
        return entityFilePathAndName;
    }
    public int getUniqueId() {
        return uniqueId;
    }
    public ArrayList<String> getFieldsName() {
        return fieldsName;
    }
    public BaseConfig getBaseConfig() {
        return baseConfig;
    }
    public ArrayList<Type> getFieldsType() {
        return fieldsType;
    }
    public ArrayList<Object> getAllFields() {
        return fields;
    }

    // setters
    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    // abstract methods
    public abstract void setEntityFieldsValue(ArrayList<Object> fieldsValue);
    protected abstract ArrayList<Object> setAllFields();
    protected abstract ArrayList<Type> setFieldsType();
    protected abstract void cloneObject(Entity entity);

    // methods
    public void printAllObjects() { //check if it can implement in Entity
        try {
            FileInputStream fis = new FileInputStream(this.getEntityFilePathAndName());
            AppendableObjectInputStream ois = new AppendableObjectInputStream(fis);

            int objectCount = this.objectCount();

            for (int i = 0; i < objectCount; i++) {
                Entity entity = this.adaptor.readRecord(this, ois);
                System.out.println(entity);
            }
            ois.close();
        } catch (Exception e) {
            System.out.println("Exception in print all objects");
        }
    }
    int objectCount() {
        int count = 0;
        try {
            FileInputStream fis = new FileInputStream(this.getEntityFilePathAndName());
            AppendableObjectInputStream ois = new AppendableObjectInputStream(fis);

            while (true){
                this.adaptor.readRecord(this, ois);
                count++;
            }
//            ois.close();
        } catch (Exception e) {
            return count;
        }
    }
    public void create() throws Exception {
        adaptor.writeRecord(this);
    }
    public ArrayList<Integer> find(final int option) {return new ArrayList<>();}
    public void get(final int index) throws Exception {
        int objectsCount = this.objectCount();
        if (index > objectsCount || index < 0) {
            throw new Exception("Exception Get Method In Entity:Index Out Of Range");
        }

        try {
            FileInputStream fis = new FileInputStream(this.getEntityFilePathAndName());
            AppendableObjectInputStream ois = new AppendableObjectInputStream(fis);

            Entity entity = null;
            for (int i = 0; i < index; i++) {
                entity = this.adaptor.readRecord(this, ois);
            }
            this.cloneObject(entity);
            ois.close();
        } catch (Exception e) {
            System.out.println("Exception in get object");
        }
    }
    public void edit(final int option, final int index) {}
    public void delete(final int index) {}
}
