package model.entities;

import controller.adaptors.Adaptor;
import model.CustomDate;
import model.configs.BaseConfig;
import model.configs.BookConfig;
import model.configs.LibraryRecordConfig;

import java.util.ArrayList;

public class Record extends Entity{
    private int studentId;
    private int bookId;
    private int intLoanedDate;
    private int intReturnDate;
    private final String constFileName = "Record";

    // constructors
    public Record(Adaptor adaptor, LibraryRecordConfig config) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFileName = constFileName;
        this.adaptor.setFileName(constFileName+".txt");
    }
    public Record(Adaptor adaptor, LibraryRecordConfig config, int studentId, int bookId, CustomDate intLoanedDate, CustomDate intReturnDate) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFileName = constFileName;
        this.adaptor.setFileName(constFileName+".txt");

        this.studentId = studentId;
        this.bookId = bookId;
        this.intLoanedDate = intLoanedDate.toInt();
        this.intReturnDate = intReturnDate.toInt();
    }

    // methods
    public boolean checkConfigValidation() {
        return false;
    }
    public void create() {}
    public ArrayList<Integer> find(final int option) {
        return new ArrayList<Integer>();
    }
    public void get(final int index) {}
    public void edit(final int option, final int index) {}
    public void delete(final int index) {}
}
