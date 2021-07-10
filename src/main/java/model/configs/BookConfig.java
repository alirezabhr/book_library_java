package model.configs;

public class BookConfig extends BaseConfig{
    private int bookNameSize = 0;
    private int bookAuthorSize = 0;
    private int bookPublisherSize = 0;

    // getters
    public int getBookNameSize() {
        return bookNameSize;
    }
    public int getBookAuthorSize() {
        return bookAuthorSize;
    }
    public int getBookPublisherSize() {
        return bookPublisherSize;
    }

    // methods
    protected void setFields(final String field, final String value) {

    }
}
