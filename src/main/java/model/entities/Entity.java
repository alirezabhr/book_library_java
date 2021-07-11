package model.entities;

import controller.adaptors.Adaptor;
import controller.configs.BaseConfig;
import controller.file_stream.AppendableObjectOutputStream;

import javax.management.ObjectName;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class Entity {
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

    // abstract methods
//    public abstract void printAllObjects(); //check if it can implement in Entity
    protected abstract ArrayList<Type> setFieldsType();
    protected abstract ArrayList<Object> setAllFields();

    // methods
    int objectCount() {
        int count = 0;
        try {
            while (true){
                this.get(count++);
            }
        } catch (IndexOutOfBoundsException exception) {
            return count-1;
        }
    }
    public void create() throws Exception {}
    public ArrayList<Integer> find(final int option) {return new ArrayList<>();}
    public void get(final int index) throws IndexOutOfBoundsException {}
    public void edit(final int option, final int index) {}
    public void delete(final int index) {}
}
