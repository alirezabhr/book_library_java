package model.entities;

import controller.adaptors.Adaptor;
import model.CustomDate;
import controller.configs.LibraryRecordConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Record extends Entity{
    private int studentId;
    private int bookId;
    private int intLoanedDate;
    private int intReturnDate;
    private final String constObjectName = "Record";

    // constructors
    public Record(LibraryRecordConfig config) {
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fields = this.setAllFields();
        this.fieldsType = this.setFieldsType();
    }
    public Record(LibraryRecordConfig config, int studentId, int bookId, CustomDate intLoanedDate, CustomDate intReturnDate) {
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fieldsType = this.setFieldsType();

        this.studentId = studentId;
        this.bookId = bookId;
        this.intLoanedDate = intLoanedDate.toInt();
        this.intReturnDate = intReturnDate.toInt();

        this.fields = this.setAllFields();
    }

    // methods
    protected ArrayList<Type> setFieldsType() {
        ArrayList<Type> arr = new ArrayList<>();
        arr.add(Integer.class);
        arr.add(Integer.class);
        arr.add(Integer.class);
        arr.add(Integer.class);
        return arr;
    }
    protected ArrayList<Object> setAllFields() {
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(studentId);
        arr.add(bookId);
        arr.add(intLoanedDate);
        arr.add(intReturnDate);
        return arr;
    }
    public void setEntityFieldsValue(ArrayList<Object> fieldsValue) {
        this.studentId = (Integer) fieldsValue.get(0);
        this.bookId = (Integer) fieldsValue.get(1);
        this.intLoanedDate = (Integer) fieldsValue.get(2);
        this.intReturnDate = (Integer) fieldsValue.get(3);
    }
}
