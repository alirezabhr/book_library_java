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
}
