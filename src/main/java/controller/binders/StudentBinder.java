package controller.binders;

import controller.adaptors.Adaptor;
import controller.configs.StudentConfig;
import controller.utils;
import model.entities.Entity;
import model.entities.Student;
import constant.MyConst;

import java.util.ArrayList;

public class StudentBinder {
    private static Student createTmpObject() {
        StudentConfig config = new StudentConfig(MyConst.constStudentConfigFilePathName);
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
}
