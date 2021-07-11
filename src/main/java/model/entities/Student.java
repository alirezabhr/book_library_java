package model.entities;

import controller.adaptors.Adaptor;
import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;
import controller.configs.StudentConfig;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Student extends Entity{
    private int studentId;
    private String name;
    private String lastName;
    private final String constObjectName = "Student";

    // constructors
    public Student(StudentConfig config) {
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fieldsType = this.setFieldsType();
    }
    public Student(StudentConfig config, int studentId, String name, String lastName) {
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fieldsType = this.setFieldsType();

        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;
    }

    // methods
    protected ArrayList<Type> setFieldsType() {
        ArrayList<Type> arr = new ArrayList<>();
        arr.add(Integer.class);
        arr.add(String.class);
        arr.add(String.class);
        return arr;
    }
}
