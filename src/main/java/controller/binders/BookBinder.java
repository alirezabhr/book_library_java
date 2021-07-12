package controller.binders;

import controller.adaptors.Adaptor;
import controller.configs.BookConfig;
import controller.utils;
import model.entities.Entity;
import model.entities.Book;
import constant.MyConst;

import java.util.ArrayList;

public class BookBinder {
    private static Book createTmpObject() {
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
}
