package model.configs;

public class BookConfig extends BaseConfig{
    private int nameSize = 0;
    private int authorSize = 0;
    private int publisherSize = 0;

    // constructor
    public BookConfig(String fileName) {
        this.configFileName = fileName;
        try {
            this.createConfig();
        } catch (Exception exception) {
            System.out.println("Exception: Book Config");
            exception.printStackTrace();
        }
    }

    // getters
    public int getNameSize() {
        return nameSize;
    }
    public int getAuthorSize() {
        return authorSize;
    }
    public int getPublisherSize() {
        return publisherSize;
    }

    // methods
    protected void setFields(final String field, final String value) throws Exception {
        if (field.equals("RECORD_MODE") || field.equals("STRING_MODE") || field.equals("RECORD_SIZE")) {
            this.setBaseFields(field, value);
        } else if (field.equals("BOOK_NAME_SIZE")) {
            if (this.isIntNumValid(value)) {
                this.nameSize = Integer.parseInt(value);
            } else {
                throw new Exception("Book Name Size is not valid");
            }
        } else if (field.equals("BOOK_AUTHOR_SIZE")) {
            if (this.isIntNumValid(value)) {
                this.authorSize = Integer.parseInt(value);
            } else {
                throw new Exception("Book Author Size is not valid");
            }
        } else if (field.equals("BOOK_PUBLISHER_SIZE")) {
            if (this.isIntNumValid(value)) {
                this.publisherSize = Integer.parseInt(value);
            } else {
                throw new Exception("Book Publisher Size is not valid");
            }
        } else {
            System.out.println("Book Config value or key is not valid");
            throw new Exception("Invalid Book Config");
        }
    }
    public boolean checkSizes(int... fieldsSize) {
        if (fieldsSize.length != 4) {
            return false;
        }

        final int sizeofOnLoanId = Integer.BYTES;
        final int sizeofIsbn = Long.BYTES;
        int nameSize = fieldsSize[0];
        int authorSize = fieldsSize[1];
        int publisherSize = fieldsSize[2];
        int recordSize = sizeofIsbn + sizeofOnLoanId + nameSize + authorSize + publisherSize;

        if (this.stringMode.equals("Fix")) {
            if (nameSize > this.nameSize || authorSize > this.authorSize || publisherSize > this.publisherSize) {
                return false;
            }
        }
        if (this.recordMode.equals("Fix")) {
            if (recordSize > this.recordSize) {
                return false;
            }
        }

        return true;
    }
}
