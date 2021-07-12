package model.entities;

import constant.MyConst;
import controller.adaptors.Adaptor;
import controller.configs.BookConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Book extends Entity{
    private Long isbn;
    private Integer onLoan;     // 0 if nobody loan this book, or student id if someone loan it
    private String name;
    private String author;
    private String publisher;

    // constructors
    public Book(Adaptor adaptor, BookConfig config) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = MyConst.constBookDatabaseFilePathName;
        this.fields = this.setAllFields();
        this.fieldsType = this.setFieldsType();
    }
    public Book(Adaptor adaptor, BookConfig config, long isbn, String name, String author, String publisher) {
        this.adaptor = adaptor;
        this.baseConfig = config;
        this.entityFilePathAndName = MyConst.constBookDatabaseFilePathName;
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

    // setters
    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublisher(String publisher) {
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
