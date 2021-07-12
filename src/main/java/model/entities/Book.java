package model.entities;

import controller.adaptors.Adaptor;
import controller.configs.BookConfig;
import controller.configs.StudentConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Book extends Entity{
    private Long isbn;
    private Integer onLoan;     // 0 if nobody loan this book, or student id if someone loan it
    private String name;
    private String author;
    private String publisher;
    private final String constObjectName = "Book";

    // constructors
    public Book(Adaptor adaptor, BookConfig config) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fields = this.setAllFields();
        this.fieldsType = this.setFieldsType();
    }
    public Book(Adaptor adaptor, BookConfig config, long isbn, String name, String author, String publisher) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = this.constBaseFilePath + constObjectName + ".txt";
        this.fieldsType = this.setFieldsType();

        this.isbn = isbn;
        this.onLoan = 0;
        this.name = name;
        this.author = author;
        this.publisher = publisher;

        this.fields = this.setAllFields();
    }

    // getters
    public Long getIsbn() {
        return isbn;
    }
    public Integer getOnLoan() {
        return onLoan;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
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
    protected ArrayList<Object> setAllFields() {
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(isbn);
        arr.add(onLoan);
        arr.add(name);
        arr.add(author);
        arr.add(publisher);
        return arr;
    }
    public void setEntityFieldsValue(ArrayList<Object> fieldsValue) {
        this.isbn = (Long) fieldsValue.get(0);
        this.onLoan = (Integer) fieldsValue.get(1);
        this.name = (String) fieldsValue.get(2);
        this.author = (String) fieldsValue.get(3);
        this.publisher = (String) fieldsValue.get(4);
    }

    @Override
    public String toString() {
        return "Book{" +
                "uniqueId=" + uniqueId +
                ", isbn=" + isbn +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", onLoan=" + onLoan +
                '}';
    }
    @Override
    public Object clone() {
        Book book = new Book(this.adaptor, (BookConfig) this.baseConfig, this.isbn, this.name, this.author, this.publisher);
        book.uniqueId = this.uniqueId;
        book.onLoan = this.onLoan;
        return book;
    }
}
