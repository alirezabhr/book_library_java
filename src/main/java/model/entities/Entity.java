package model.entities;

import controller.adaptors.Adaptor;
import controller.configs.BaseConfig;
import controller.file_stream.AppendableObjectInputStream;

import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class Entity {
    protected Adaptor adaptor;
    protected BaseConfig baseConfig;
    protected Integer uniqueId;
    protected String entityFilePathAndName;
    protected ArrayList<String> fieldsName;
    protected ArrayList<Type> fieldsType;
    protected ArrayList<Object> fields;

    // getters
    public String getEntityFilePathAndName() {
        return entityFilePathAndName;
    }
    public Integer getUniqueId() {
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
    public abstract Object clone();
    public abstract void setEntityFieldsValue(ArrayList<Object> fieldsValue);
    protected abstract ArrayList<Object> setAllFields();
    protected abstract ArrayList<Type> setFieldsType();

    // methods
    public void printAllObjects() {
        ArrayList<Entity> entities = this.getAllObjects();
        for (Entity entity : entities) {
            System.out.println(entity);
        }
    }
    public ArrayList<Entity> getAllObjects() {
        ArrayList<Entity> arr = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(this.getEntityFilePathAndName());
            AppendableObjectInputStream ois = new AppendableObjectInputStream(fis);

            int objectCount = this.objectCount();

            for (int i = 0; i < objectCount; i++) {
                Entity entity = this.adaptor.readRecord(this, ois);
                arr.add((Entity) entity.clone());
            }
            ois.close();
        } catch (Exception e) {
            System.out.println("Exception in print all objects");
        }

        return arr;
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
        if (index > objectsCount || index <= 0) {
            throw new Exception("Exception Get Method In Entity:Index Out Of Range");
        }

        try {
            FileInputStream fis = new FileInputStream(this.getEntityFilePathAndName());
            AppendableObjectInputStream ois = new AppendableObjectInputStream(fis);

            Entity entity = null;
            for (int i = 0; i < index; i++) {
                entity = this.adaptor.readRecord(this, ois);
            }
            this.setEntityFieldsValue(entity.setAllFields());
            ois.close();
        } catch (Exception e) {
            System.out.println("Exception in get object");
        }
    }
    public void edit(final int option, final int index) {}
    public void delete(final int index) throws Exception {
        int objCount = this.objectCount();
        if (index > objCount || index <= 0) {
            throw new Exception("Exception Delete Method In Entity:Index Out Of Range");
        }

        ArrayList<Entity> arr1 = new ArrayList<>();
        ArrayList<Entity> arr2 = new ArrayList<>();

        for (int i = 1; i < index; i++) {
            this.get(i);
            arr1.add((Entity) this.clone());
        }
        for (int i = index+1; i <= objCount; i++) {
            this.get(i);
            arr2.add((Entity) this.clone());
        }

        adaptor.createEmptyFile(this.entityFilePathAndName);

        for (Entity entity : arr1) {
            entity.create();
        }
        for (Entity entity : arr2) {
            entity.create();
        }
    }
}
