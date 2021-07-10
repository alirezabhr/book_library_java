package model.entities;

import controller.adaptors.Adaptor;
import controller.file_stream.AppendableObjectOutputStream;
import controller.configs.StudentConfig;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Student extends Entity{
    private int studentId;
    private String name;
    private String lastName;
    private final String constFileName = "Student";

    // constructors
    public Student(Adaptor adaptor, StudentConfig config) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFileName = constFileName;
        this.adaptor.setFileName(constFileName+".txt");
    }
    public Student(Adaptor adaptor, StudentConfig config, int studentId, String name, String lastName) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFileName = constFileName;
        this.adaptor.setFileName(constFileName+".txt");

        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;
    }

    // methods
    public boolean checkConfigValidation() {
        return false;
    }
    public void create() throws Exception {
        final int sizeofInt = 4;
        int recordSize = sizeofInt + this.name.length() + this.lastName.length();
        int studentId = this.studentId;
        int nameSize = this.name.length();
        int lastNameSize = this.lastName.length();


        boolean isValid = baseConfig.checkSizes(nameSize, lastNameSize);
        if (!isValid) {
            System.out.println("Add Student Finished, Unsuccessfully!");
            throw new Exception("exception in create student.\nconfig is not valid");
        }

//        int lastId = getLastObjectId();        ///todo should change
        int lastId = 12;        //fake todo should remove

        try {
            FileOutputStream fos = new FileOutputStream("./database/t.txt", true);
            AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos);

            adaptor.writeRecord(oos, recordSize, lastId);
            adaptor.writeIntField(oos, studentId);
            adaptor.writeStringField(oos, this.name);
            adaptor.writeStringField(oos, this.lastName);

            oos.close();
        } catch (IOException exception) {
            System.out.println("(IO)Exception: in create student");
            exception.printStackTrace();
        }
    }
    public ArrayList<Integer> find(final int option) {
        return new ArrayList<Integer>();
    }
    public void get(final int index) {}
    public void edit(final int option, final int index) {}
    public void delete(final int index) {}
}
