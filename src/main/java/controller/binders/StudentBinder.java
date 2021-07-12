package controller.binders;

import controller.adaptors.Adaptor;
import controller.configs.BaseConfig;
import controller.configs.StudentConfig;
import controller.utils;
import model.entities.Entity;
import model.entities.Student;
import constant.MyConst;

import java.util.ArrayList;

import static controller.utils.isIntNumber;

public class StudentBinder {
    private static BaseConfig getConfig() {
        return new StudentConfig(MyConst.constStudentConfigFilePathName);
    }
    public static Student createTmpObject() {
        StudentConfig config = (StudentConfig) getConfig();
        Adaptor adaptor = utils.getAdaptor(config);
        return new Student(adaptor, config);
    }
    public static ArrayList<Student> getAllStudents() {
        Student tmpStudent = createTmpObject();
        ArrayList<Entity> entities = tmpStudent.getAllObjects();

        ArrayList<Student> students = new ArrayList<>();
        for (Entity e : entities) {
            students.add((Student) e);
        }

        return students;
    }
    public static String checkStudentValidation(String... fields) {
        String name = fields[0];
        String lastName = fields[1];
        String stdIdString = fields[2];
        int stdId;

        if (!isIntNumber(stdIdString)) {
            return "Student Id Must Be A Number";
        }
        stdId = Integer.parseInt(stdIdString);

        StudentConfig config = (StudentConfig) getConfig();
        Adaptor adaptor = utils.getAdaptor(config);
        Student student = new Student(adaptor, config, stdId, name, lastName);

        if (!adaptor.isValidObject(student)) {
            return "Not A Valid Student With This Student Config!";
        }

        return "valid";
    }
    public static Student getStudent(Object... objects) {
        String name = (String) objects[0];
        String lastName = (String) objects[1];
        Integer stdIdString = (Integer) objects[2];

        StudentConfig config = (StudentConfig) getConfig();
        Adaptor adaptor = utils.getAdaptor(config);
        return new Student(adaptor, config, stdIdString, name, lastName);
    }
}
