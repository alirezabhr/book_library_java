package controller.binders;

import controller.adaptors.Adaptor;
import controller.configs.BaseConfig;
import controller.configs.LibraryRecordConfig;
import controller.utils;
import model.CustomDate;
import model.entities.Entity;
import model.entities.Record;
import constant.MyConst;

import java.util.ArrayList;

import static controller.utils.isIntNumber;

public class RecordBinder {
    private static BaseConfig getConfig() {
        return new LibraryRecordConfig(MyConst.constRecordConfigFilePathName);
    }
    private static Record createTmpObject() {
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
}
