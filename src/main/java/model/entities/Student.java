package model.entities;

import java.util.ArrayList;

public class Student extends Entity{
    private int studentId;
    private String name;
    private String lastName;
    private final String constFileName = "Student";

    public Student() {
        this.entityFileName = constFileName;
//        this.adaptor.setFileName(constFileName+".txt");
    }

    public Student(int studentId, String name, String lastName) {
        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;

        this.entityFileName = constFileName;
//        this.adaptor.setFileName(constFileName+".txt");
    }

    public void create() {}
    public ArrayList<Integer> find(int option) {
        return new ArrayList<Integer>();
    }
    public void get(int index) {}
    public void edit(int option, int index) {}
    public void delete(int index) {}
}
