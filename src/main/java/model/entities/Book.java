package model.entities;

import controller.adaptors.Adaptor;
import controller.configs.BookConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Book extends Entity{
    private long isbn;
    private int onLoan;     // 0 if nobody loan this book, or student id if someone loan it
    private String name;
    private String author;
    private String publisher;
    private final String constObjectName = "Book";

    // constructors
    public Book(BookConfig config) {
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fieldsType = this.setFieldsType();
    }
    public Book(BookConfig config, long isbn, String name, String author, String publisher) {
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fieldsType = this.setFieldsType();

        this.isbn = isbn;
        this.onLoan = 0;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
    }

    // methods
    protected ArrayList<Type> setFieldsType() {
        ArrayList<Type> arr = new ArrayList<>();
        arr.add(Long.class);
        arr.add(Integer.class);
        arr.add(String.class);
        arr.add(String.class);
        arr.add(String.class);
        return arr;
    }
}
