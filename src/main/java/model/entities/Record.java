package model.entities;

import constant.MyConst;
import controller.adaptors.Adaptor;
import controller.binders.BookBinder;
import controller.binders.StudentBinder;
import model.CustomDate;
import controller.configs.LibraryRecordConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Record extends Entity{
    private Integer studentId;
    private Integer bookId;
    private Integer intLoanedDate;
    private Integer intReturnDate;

    // constructors
    public Record(Adaptor adaptor, LibraryRecordConfig config) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = MyConst.constRecordDatabaseFilePathName;
        this.fields = this.setAllFields();
        this.fieldsType = this.setFieldsType();
    }
    public Record(Adaptor adaptor, LibraryRecordConfig config, int studentId, int bookId, CustomDate loanedDate, CustomDate returnDate) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = MyConst.constRecordDatabaseFilePathName;
        this.fieldsType = this.setFieldsType();

        this.studentId = studentId;
        this.bookId = bookId;
        this.intLoanedDate = loanedDate.toInt();
        this.intReturnDate = returnDate.toInt();

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

    // setters
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public void setIntLoanedDate(CustomDate loanedDate) {
        this.intLoanedDate = loanedDate.toInt();
    }
    public void setIntReturnDate(CustomDate returnDate) {
        this.intReturnDate = returnDate.toInt();
    }

    // methods
    @Override
    public void create() throws Exception {
        int studentId = this.studentId;
        int bookId = this.bookId;
        adaptor.writeRecord(this);
        this.changeBookOnLoan(bookId, studentId);
    }
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
    public void checkValidation() throws Exception {
        this.checkStudentIdValidation();
        this.checkBookIdValidation();
        this.checkDateValidation();
    }
    private void checkStudentIdValidation() throws Exception {
        Student tmpStudent = StudentBinder.createTmpObject();
        try {
            tmpStudent.get(this.studentId);
        } catch (Exception exception) {
            throw new Exception("Student With This Unique Id Does Not Exist");
        }
    }
    private void checkBookIdValidation() throws Exception {
        Book tmpBook = BookBinder.createTmpObject();
        try {
            tmpBook.get(this.bookId);
        } catch (Exception e) {
            throw new Exception("Book With This Unique Id Does Not Exist");
        }
        if (tmpBook.getOnLoan() != 0) {
            throw new Exception("This Book Has Been Loaned");
        }
    }
    private void checkDateValidation() throws Exception {
        if (this.intReturnDate <= this.intLoanedDate) {
            throw new Exception("Return Date Is Before Loan Date!");
        }
    }
    private void changeBookOnLoan(int bookId, int studentId) {
        Book book = BookBinder.createTmpObject();

        try {
            book.get(bookId);
        } catch (Exception e) {
            System.out.println("exception in change book on loan");
        }

//        book.edit();        // todo edit book should implemented
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
