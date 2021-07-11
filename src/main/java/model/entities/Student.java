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
    public Student(Adaptor adaptor, StudentConfig config) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fields = this.setAllFields();
        this.fieldsType = this.setFieldsType();
    }
    public Student(Adaptor adaptor, StudentConfig config, int studentId, String name, String lastName) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fieldsType = this.setFieldsType();

        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;

        this.fields = this.setAllFields();
    }

    // getters
    public int getStudentId() {
        return studentId;
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }

    // methods
    protected ArrayList<Type> setFieldsType() {
        ArrayList<Type> arr = new ArrayList<>();
        arr.add(Integer.class);
        arr.add(String.class);
        arr.add(String.class);
        return arr;
    }
    protected ArrayList<Object> setAllFields() {
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(studentId);
        arr.add(name);
        arr.add(lastName);
        return arr;
    }
    public void setEntityFieldsValue(ArrayList<Object> fieldsValue) {
        this.studentId = (Integer) fieldsValue.get(0);
        this.name = (String) fieldsValue.get(1);
        this.lastName = (String) fieldsValue.get(2);
    }

    @Override
    public String toString() {
        return "Student{" +
                "uniqueId=" + uniqueId +
                ", studentId=" + studentId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
    @Override
    public Object clone() {
        Student student = new Student(this.adaptor, (StudentConfig) this.baseConfig, this.studentId, this.name, this.lastName);
        student.uniqueId = this.uniqueId;
        return student;
    }
}
