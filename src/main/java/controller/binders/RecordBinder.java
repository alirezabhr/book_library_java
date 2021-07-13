package controller.binders;

import controller.adaptors.Adaptor;
import controller.configs.BaseConfig;
import controller.configs.LibraryRecordConfig;
import controller.utils;
import model.CustomDate;
import model.entities.Entity;
import model.entities.Record;
import constant.MyConst;
import model.entities.Student;

import java.util.ArrayList;
import java.util.Arrays;

import static controller.utils.isIntNumber;

public class RecordBinder {
    private static BaseConfig getConfig() {
        return new LibraryRecordConfig(MyConst.constRecordConfigFilePathName);
    }
    public static Record createTmpObject() {
        LibraryRecordConfig config = new LibraryRecordConfig(MyConst.constRecordConfigFilePathName);
        Adaptor adaptor = utils.getAdaptor(config);
        return new Record(adaptor, config);
    }
    public static ArrayList<Record> getAllRecords() {
        Record tmpRecord = createTmpObject();
        ArrayList<Entity> entities = tmpRecord.getAllObjects();

        ArrayList<Record> records = new ArrayList<>();
        for (Entity e : entities) {
            records.add((Record) e);
        }

        return records;
    }
    public static String checkRecordValidation(String... fields) {
        String stdIdString = fields[0];
        String bookIdString = fields[1];
        String lyString = fields[2];
        String lmString = fields[3];
        String ldString = fields[4];
        String ryString = fields[5];
        String rmString = fields[6];
        String rdString = fields[7];
        int studentId;
        int bookId;

        if (!isIntNumber(stdIdString)) {
            return "Student Unique Id Must Be A Number";
        }
        if (!isIntNumber(bookIdString)) {
            return "Book Unique Id Must Be A Number";
        }

        if (!isIntNumber(lyString) || !isIntNumber(lmString) || !isIntNumber(ldString)) {
            return "Loan Date Must Be A Number";
        }
        if (!isIntNumber(ryString) || !isIntNumber(rmString) || !isIntNumber(rdString)) {
            return "Return Date Must Be A Number";
        }

        studentId = Integer.parseInt(stdIdString);
        bookId = Integer.parseInt(bookIdString);
        CustomDate loanDate = new CustomDate(Integer.parseInt(lyString), Integer.parseInt(lmString), Integer.parseInt(ldString));
        CustomDate returnDate = new CustomDate(Integer.parseInt(ryString), Integer.parseInt(rmString), Integer.parseInt(rdString));

        if (!loanDate.isValid()) {
            return "Loan Date Is Not A Valid Date";
        }
        if (!returnDate.isValid()) {
            return "Return Date Is Not A Valid Date";
        }

        LibraryRecordConfig config = (LibraryRecordConfig) getConfig();
        Adaptor adaptor = utils.getAdaptor(config);
        Record record = new Record(adaptor, config, studentId, bookId, loanDate, returnDate);

        try {
            record.checkValidation();
        } catch (Exception exception) {
            return exception.getMessage();
        }

        if (!adaptor.isValidObject(record)) {
            return "Not A Valid Record With This Record Config!";
        }

        return "valid";
    }
    public static Record getRecord(Object... objects) {
        Integer studentId = (Integer) objects[0];
        Integer bookId = (Integer) objects[1];
        CustomDate loanDate = (CustomDate) objects[2];
        CustomDate returnDate = (CustomDate) objects[3];

        LibraryRecordConfig config = (LibraryRecordConfig) getConfig();
        Adaptor adaptor = utils.getAdaptor(config);
        return new Record(adaptor, config, studentId, bookId, loanDate, returnDate);
    }
    public static void editRecord(int uniqueId, Object... objects) throws Exception {
        Record record = createTmpObject();
        record.get(uniqueId);

        int stdId = Integer.parseInt((String) objects[0]);
        int bookId = Integer.parseInt((String) objects[1]);
        int ly = Integer.parseInt((String) objects[2]);
        int lm = Integer.parseInt((String) objects[3]);
        int ld = Integer.parseInt((String) objects[4]);
        int ry = Integer.parseInt((String) objects[5]);
        int rm = Integer.parseInt((String) objects[6]);
        int rd = Integer.parseInt((String) objects[7]);

        CustomDate loanDate = new CustomDate(ly, lm, ld);
        CustomDate returnDate = new CustomDate(ry, rm, rd);

        record.setStudentId(stdId);
        record.setBookId(bookId);
        record.setIntLoanedDate(loanDate);
        record.setIntReturnDate(returnDate);

        record.edit();
    }
    public static boolean deleteObject(int uniqueId) {
        Record record = createTmpObject();
        try {
            record.delete(uniqueId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static ArrayList<Record> getFilteredData(String... filterParams) throws Exception {
        System.out.println(Arrays.toString(filterParams));
        int studentId = 0;
        int bookId = 0;
        String loanDateStr = filterParams[2];
        String returnDateStr = filterParams[3];
        int loanDate = 0;
        int returnDate = 0;

        if (!filterParams[0].equals("")) {
            try {
                studentId  = Integer.parseInt(filterParams[0]);
            } catch (Exception exception) {
                throw new Exception("Student Id Should Be A Number");
            }
        }
        if (!filterParams[1].equals("")) {
            try {
                bookId  = Integer.parseInt(filterParams[1]);
            } catch (Exception exception) {
                throw new Exception("Book Id Should Be A Number");
            }
        }
        if (!filterParams[2].equals("")) {
            loanDate = CustomDate.dateStringToInt(loanDateStr);
        }
        if (!filterParams[3].equals("")) {
            returnDate = CustomDate.dateStringToInt(returnDateStr);
        }


        Record record = RecordBinder.createTmpObject();
        ArrayList<Entity> tmpRecordList = record.getAllObjects();
        ArrayList<Record> records = new ArrayList<>();
        for (Entity entity : tmpRecordList) {
            records.add((Record) entity);
        }
        Object[] arr = records.toArray();

        if (!filterParams[0].equals("")) {
            int finalStdId = studentId;
            arr = records.stream().filter(ev -> (ev).getStudentId().equals(finalStdId)).toArray();
        }
        records = getNewObjectList(arr);

        if (!filterParams[1].equals("")) {
            int finalBookId = bookId;
            arr = records.stream().filter(ev -> (ev).getBookId().equals(finalBookId)).toArray();
        }
        records = getNewObjectList(arr);

        if (!filterParams[2].equals("")) {
            int finalLoanDate = loanDate;
            arr = records.stream().filter(ev -> (ev).getIntLoanedDate().equals(finalLoanDate)).toArray();
        }
        records = getNewObjectList(arr);

        if (!filterParams[3].equals("")) {
            int finalReturnDate = returnDate;
            arr = records.stream().filter(ev -> (ev).getIntReturnDate().equals(finalReturnDate)).toArray();
        }
        records = getNewObjectList(arr);

        return records;

    }
    private static ArrayList<Record> getNewObjectList(Object[] objects) {
        ArrayList<Record> records = new ArrayList<>();
        for (Object obj : objects) {
            records.add((Record) obj);
        }
        return records;
    }
}
