package model.entities;

import controller.adaptors.Adaptor;
import controller.configs.BaseConfig;
import controller.file_stream.AppendableObjectOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Entity {
    protected Adaptor adaptor;
    protected BaseConfig baseConfig;
    protected int uniqueId;
    protected final String constBaseFilePath = "./database/";
    protected String entityFileName;
    protected ArrayList<String> fieldsName;

    // getters
    public String getEntityFileName() {
        return entityFileName;
    }
    public int getUniqueId() {
        return uniqueId;
    }
    public ArrayList<String> getFieldsName() {
        return fieldsName;
    }

    // abstract methods
//    public abstract void printAllObjects(); //check if it can implement in Entity
    public abstract void create() throws Exception;
    public abstract ArrayList<Integer> find(final int option);
    public abstract void get(final int index) throws IndexOutOfBoundsException;
    public abstract void edit(final int option, final int index);
    public abstract void delete(final int index);

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
}
