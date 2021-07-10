package model.entities;

import controller.adaptors.Adaptor;
import controller.configs.BaseConfig;

import java.util.ArrayList;

public abstract class Entity {
    protected Adaptor adaptor;
    protected BaseConfig baseConfig;
    protected int uniqueId;
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
    public abstract boolean checkConfigValidation();
    public abstract ArrayList<Integer> find(final int option);
    public abstract void get(final int index);
    public abstract void edit(final int option, final int index);
    public abstract void delete(final int index);

    // methods
    //    int objectCount();
}
