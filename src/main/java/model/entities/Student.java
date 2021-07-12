package model.entities;

import constant.MyConst;
import controller.adaptors.Adaptor;
import controller.configs.StudentConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Student extends Entity{
    private Integer studentId;
    private String name;
    private String lastName;

    // constructors
    public Student(Adaptor adaptor, StudentConfig config) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = MyConst.constStudentDatabaseFilePathName;
        this.fields = this.setAllFields();
        this.fieldsType = this.setFieldsType();
    }
    public Student(Adaptor adaptor, StudentConfig config, int studentId, String name, String lastName) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = MyConst.constStudentDatabaseFilePathName;
        this.fieldsType = this.setFieldsType();

        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;

        this.fields = this.setAllFields();
    }

    // getters
    public Integer getStudentId() {
        return studentId;
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }

    // setters
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
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
