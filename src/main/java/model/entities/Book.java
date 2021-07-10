package model.entities;

import controller.adaptors.Adaptor;
import controller.configs.BookConfig;

import java.util.ArrayList;

public class Book extends Entity{
    private long isbn;
    private int onLoan;     // 0 if nobody loan this book, or student id if someone loan it
    private String name;
    private String author;
    private String publisher;
    private final String constFileName = "Book";

    // constructors
    public Book(Adaptor adaptor, BookConfig config) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFileName = this.constBaseFilePath + constFileName + ".txt";
        this.adaptor.setFileName(constFileName+".txt");
    }
    public Book(Adaptor adaptor, BookConfig config, long isbn, String name, String author, String publisher) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFileName = this.constBaseFilePath + constFileName + ".txt";
        this.adaptor.setFileName(constFileName+".txt");

        this.isbn = isbn;
        this.onLoan = 0;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
    }

    // methods
    public void create() {}
    public ArrayList<Integer> find(final int option) {
        return new ArrayList<Integer>();
    }
    public void get(final int index) throws IndexOutOfBoundsException {}
    public void edit(final int option, final int index) {}
    public void delete(final int index) {}
}
