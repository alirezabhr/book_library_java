package model.entities;

import model.CustomDate;

import java.util.ArrayList;

public class Record extends Entity{
    private int studentId;
    private int bookId;
    private int intLoanedDate;
    private int intReturnDate;
    private final String constFileName = "Record";

    public Record() {
        this.entityFileName = constFileName;
//        this.adaptor.setFileName(constFileName+".txt");
    }

    public Record(int studentId, int bookId, CustomDate intLoanedDate, CustomDate intReturnDate) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.intLoanedDate = intLoanedDate.toInt();
        this.intReturnDate = intReturnDate.toInt();

        this.entityFileName = constFileName;
//        this.adaptor.setFileName(constFileName+".txt");
    }

    public void create() {}
    public ArrayList<Integer> find(int option) {
        return new ArrayList<Integer>();
    }
    public void get(int index) {}
    public void edit(int option, int index) {}
    public void delete(int index) {}
}
