package controller.binders;

import controller.adaptors.Adaptor;
import controller.configs.BaseConfig;
import controller.configs.BookConfig;
import controller.utils;
import model.entities.Entity;
import model.entities.Book;
import constant.MyConst;

import java.util.ArrayList;

import static controller.utils.isLongNumber;

public class BookBinder {
    private static BaseConfig getConfig() {
        return new BookConfig(MyConst.constBookConfigFilePathName);
    }
    public static Book createTmpObject() {
        BookConfig config = new BookConfig(MyConst.constBookConfigFilePathName);
        Adaptor adaptor = utils.getAdaptor(config);
        return new Book(adaptor, config);
    }
    public static ArrayList<Book> getAllBooks() {
        Book tmpBook = createTmpObject();
        ArrayList<Entity> entities = tmpBook.getAllObjects();

        ArrayList<Book> books = new ArrayList<>();
        for (Entity e : entities) {
            books.add((Book) e);
        }

        return books;
    }
    public static String checkBookValidation(String... fields) {
        String name = fields[0];
        String author = fields[1];
        String publisher = fields[2];
        String isbnStr = fields[3];
        long isbn;

        if (!isLongNumber(isbnStr)) {
            return "Book ISBN Must Be A Number";
        }
        isbn = Long.parseLong(isbnStr);

        BookConfig config = (BookConfig) getConfig();
        Adaptor adaptor = utils.getAdaptor(config);
        Book book = new Book(adaptor, config, isbn, name, author, publisher);

        if (!adaptor.isValidObject(book)) {
            return "Not A Valid Book With This Book Config!";
        }

        return "valid";
    }
    public static Book getBook(Object... objects) {
        String name = (String) objects[0];
        String author = (String) objects[1];
        String publisher = (String) objects[2];
        Long isbn = (Long) objects[3];

        BookConfig config = (BookConfig) getConfig();
        Adaptor adaptor = utils.getAdaptor(config);
        return new Book(adaptor, config, isbn, name, author, publisher);
    }
    public static void editBook(int uniqueId, Object... objects) throws Exception {
        Book book = createTmpObject();
        book.get(uniqueId);

        String name = (String) objects[0];
        String author = (String) objects[1];
        String publisher = (String) objects[2];
        Long isbn = (Long) objects[3];

        book.setName(name);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setIsbn(isbn);

        book.edit();
    }
}
