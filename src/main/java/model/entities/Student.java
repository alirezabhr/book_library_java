package model.entities;

import controller.adaptors.Adaptor;
import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;
import controller.configs.StudentConfig;

import java.io.FileInputStream;
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
        this.entityFileName = this.constBaseFilePath + constFileName + ".txt";
        this.adaptor.setFileName(constFileName+".txt");
    }
    public Student(Adaptor adaptor, StudentConfig config, int studentId, String name, String lastName) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFileName = this.constBaseFilePath + constFileName + ".txt";
        this.adaptor.setFileName(constFileName+".txt");

        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;
    }

    // methods
    public void create() throws Exception {
        final int sizeofInt = 4;
        int recordSize = sizeofInt + this.name.length() + this.lastName.length();
        int studentId = this.studentId;
        int nameSize = this.name.length();
        int lastNameSize = this.lastName.length();
        int lastId = this.objectCount();

        boolean isValid = baseConfig.checkSizes(nameSize, lastNameSize);
        if (!isValid) {
            System.out.println("Add Student Finished, Unsuccessfully!");
            throw new Exception("exception in create student.\nconfig is not valid");
        }

        try {
            FileOutputStream fos = new FileOutputStream(this.entityFileName, true);
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
    public void get(final int index) throws IndexOutOfBoundsException {
        try {
            FileInputStream fis = new FileInputStream(this.entityFileName);
            AppendableObjectInputStream ois = new AppendableObjectInputStream(fis);

            int uniqueId = adaptor.readRecord(ois);
            int studentId = adaptor.readIntField(ois);
            String name = adaptor.readStringField(ois);
            String lastName = adaptor.readStringField(ois);

            ois.close();

            this.uniqueId = uniqueId;
            this.studentId = studentId;
            this.name = name;
            this.lastName = lastName;

        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("(IO | ClassNotFound)Exception: in get student");
            throw new IndexOutOfBoundsException();
        }
    }
    public void edit(final int option, final int index) {}
    public void delete(final int index) {}
}
