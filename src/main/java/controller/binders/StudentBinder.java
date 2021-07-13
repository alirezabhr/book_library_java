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
    public static void editStudent(int uniqueId, Object... objects) throws Exception {
        Student student = createTmpObject();
        student.get(uniqueId);

        String name = (String) objects[0];
        String lastName = (String) objects[1];
        Integer stdId = (Integer) objects[2];

        student.setName(name);
        student.setLastName(lastName);
        student.setStudentId(stdId);

        student.edit();
    }
    public static boolean deleteObject(int uniqueId) {
        Student student = createTmpObject();
        try {
            student.delete(uniqueId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static ArrayList<Student> getFilteredData(String... filterParams) throws Exception {
        String name = filterParams[0];
        String lastName = filterParams[1];
        int stdId = 0;
        if (!filterParams[2].equals("")) {
            try {
                stdId  = Integer.parseInt(filterParams[2]);
            } catch (Exception exception) {
                throw new Exception("Student Id Should Be A Number");
            }
        }

        Student student = createTmpObject();
        ArrayList<Entity> tmpStudentList = student.getAllObjects();
        ArrayList<Student> students = new ArrayList<>();
        for (Entity entity : tmpStudentList) {
            students.add((Student) entity);
        }
        Object[] arr = students.toArray();
        if (!name.equals("")) {
            arr = students.stream().filter(ev -> {
                if ((ev).getName().equals(name)) {
                    return true;
                }
                return false;
            }).toArray();
        }
        students = new ArrayList<>();
        for (Object obj : arr) {
            students.add((Student) obj);
        }
        if (!lastName.equals("")) {
            arr = students.stream().filter(ev -> {
                if ((ev).getLastName().equals(lastName)) {
                    return true;
                }
                return false;
            }).toArray();
        }
        students = new ArrayList<>();
        for (Object obj : arr) {
            students.add((Student) obj);
        }
        if (!filterParams[2].equals("")) {
            int finalStdId = stdId;
            arr = students.stream().filter(ev -> {
                if ((ev).getStudentId().equals(finalStdId)) {
                    return true;
                }
                return false;
            }).toArray();
        }
        students = new ArrayList<>();
        for (Object obj : arr) {
            students.add((Student) obj);
        }
        return students;
    }
}
