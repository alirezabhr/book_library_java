package model.entities;

import controller.adaptors.Adaptor;
import controller.configs.BookConfig;
import model.CustomDate;
import controller.configs.LibraryRecordConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Record extends Entity{
    private Integer studentId;
    private Integer bookId;
    private Integer intLoanedDate;
    private Integer intReturnDate;
    private final String constObjectName = "Record";

    // constructors
    public Record(Adaptor adaptor, LibraryRecordConfig config) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fields = this.setAllFields();
        this.fieldsType = this.setFieldsType();
    }
    public Record(Adaptor adaptor, LibraryRecordConfig config, int studentId, int bookId, CustomDate intLoanedDate, CustomDate intReturnDate) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fieldsType = this.setFieldsType();

        this.studentId = studentId;
        this.bookId = bookId;
        this.intLoanedDate = intLoanedDate.toInt();
        this.intReturnDate = intReturnDate.toInt();

        this.fields = this.setAllFields();
    }

    // getters
    public Integer getStudentId() {
        return studentId;
    }
    public Integer getBookId() {
        return bookId;
    }
    public Integer getIntLoanedDate() {
        return intLoanedDate;
    }
    public Integer getIntReturnDate() {
        return intReturnDate;
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

    @Override
    public String toString() {
        return "Record{" +
                "uniqueId=" + uniqueId +
                ", studentId=" + studentId +
                ", bookId=" + bookId +
                ", intLoanedDate=" + intLoanedDate +
                ", intReturnDate=" + intReturnDate +
                '}';
    }
    @Override
    public Object clone() {
        CustomDate loanDate = CustomDate.intToDate(this.intLoanedDate);
        CustomDate returnDate = CustomDate.intToDate(this.intReturnDate);
        Record record = new Record(this.adaptor, (LibraryRecordConfig) this.baseConfig, this.studentId, this.bookId, loanDate, returnDate);
        record.uniqueId = this.uniqueId;
        return record;
    }
}
