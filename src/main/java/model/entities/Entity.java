package model.entities;

import java.util.ArrayList;

public abstract class Entity {
    protected int uniqueId;
    protected String entityFileName;
    protected ArrayList<String> fieldsName;

    public String getEntityFileName() {
        return entityFileName;
    }
    public int getUniqueId() {
        return uniqueId;
    }
    public ArrayList<String> getFieldsName() {
        return fieldsName;
    }
//    int objectCount();
//    public abstract void printAllObjects(); //check if it can implement in Entity
//    virtual bool checkConfigValidation(Config &config) = 0;
    public abstract void create();
    public abstract ArrayList<Integer> find(int option);
    public abstract void get(int index);
    public abstract void edit(int option, int index);
    public abstract void delete(int index);
}
