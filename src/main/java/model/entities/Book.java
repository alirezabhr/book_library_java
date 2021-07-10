package model.entities;

import java.util.ArrayList;

public class Book extends Entity{
    private long isbn;
    private int onLoan;     // 0 if nobody loan this book, or student id if someone loan it
    private String name;
    private String author;
    private String publisher;
    private final String constFileName = "Book";

    public Book() {
        this.entityFileName = constFileName;
//        this.adaptor.setFileName(constFileName+".txt");
    }

    public Book(long isbn, String name, String author, String publisher) {
        this.isbn = isbn;
        this.onLoan = 0;
        this.name = name;
        this.author = author;
        this.publisher = publisher;

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
