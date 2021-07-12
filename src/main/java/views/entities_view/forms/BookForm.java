package views.entities_view.forms;

import controller.binders.BookBinder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.entities.Book;
import model.entities.Entity;
import views.widgets.CustomTextField;
import views.widgets.MessageLabel;
import views.widgets.TitleLabel;

import static controller.binders.BookBinder.checkBookValidation;
import static controller.binders.BookBinder.getBook;
import static controller.utils.isAnyEmptyField;

public class BookForm extends BaseForm{
    @Override
    public VBox createFormDetail() {
        TitleLabel label = new TitleLabel("Create Book");

        CustomTextField nameTextField = new CustomTextField("book name");
        CustomTextField authorTextField = new CustomTextField("author");
        CustomTextField publisherTextField = new CustomTextField("publisher");
        CustomTextField isbnTextField = new CustomTextField("isbn");
        isbnTextField.setMaxWidth(130);

        MessageLabel errorLabel = new MessageLabel("", "red");
        MessageLabel successLabel = new MessageLabel("", "green");

        Button button = new Button("Create");
        button.setOnAction(event -> {
            errorLabel.setText("");
            successLabel.setText("");
            if (!isAnyEmptyField(nameTextField, authorTextField, publisherTextField, isbnTextField)) {
                String validation = checkBookValidation(nameTextField.getText(), authorTextField.getText(), publisherTextField.getText(), isbnTextField.getText());
                if (validation.equals("valid")) {
                    this.createObject(nameTextField.getText(), authorTextField.getText(), publisherTextField.getText(), Long.parseLong(isbnTextField.getText()));
                    successLabel.setText("Book Created Successfully.");
                } else {
                    errorLabel.setText(validation);
                }
            } else {
                errorLabel.setText("You Have Empty Fields!");
            }
        });

        VBox root = new VBox(label, nameTextField, authorTextField, publisherTextField, isbnTextField, button, errorLabel, successLabel);
        root.setSpacing(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        return root;
    }
    @Override
    public VBox editFormDetail(Entity entity) {
        Book book = (Book) entity;
        int uniqueId = book.getUniqueId();
        TitleLabel label = new TitleLabel("Edit Book");

        CustomTextField nameTextField = new CustomTextField("book name", book.getName());
        CustomTextField authorTextField = new CustomTextField("author", book.getAuthor());
        CustomTextField publisherTextField = new CustomTextField("publisher", book.getPublisher());
        CustomTextField isbnTextField = new CustomTextField("isbn", book.getIsbn().toString());
        isbnTextField.setMaxWidth(130);

        MessageLabel errorLabel = new MessageLabel("", "red");
        MessageLabel successLabel = new MessageLabel("", "green");

        Button button = new Button("Edit");
        button.setOnAction(event -> {
            errorLabel.setText("");
            successLabel.setText("");
            if (!isAnyEmptyField(nameTextField, authorTextField, publisherTextField, isbnTextField)) {
                String validation = checkBookValidation(nameTextField.getText(), authorTextField.getText(), publisherTextField.getText(), isbnTextField.getText());
                if (validation.equals("valid")) {
                    this.editObject(uniqueId, nameTextField.getText(), authorTextField.getText(), publisherTextField.getText(), Long.parseLong(isbnTextField.getText()));
                    successLabel.setText("Book Edited Successfully.");
                } else {
                    errorLabel.setText(validation);
                }
            } else {
                errorLabel.setText("You Have Empty Fields!");
            }
        });

        VBox root = new VBox(label, nameTextField, authorTextField, publisherTextField, isbnTextField, button, errorLabel, successLabel);
        root.setSpacing(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        return root;
    }
    @Override
    public VBox deleteFormDetail() {
        return null;
    }

    @Override
    protected void createObject(Object... objects) {
        Book book = getBook(objects);
        try {
            book.create();
        } catch (Exception e) {
            System.out.println("Can Not Create A Book!!!");
        }
    }
    @Override
    protected void editObject(int uniqueId, Object... objects) {
        try {
            BookBinder.editBook(uniqueId, objects);
        } catch (Exception exception) {
            System.out.println("Can Not Edit A Book!!!");
        }
    }
    @Override
    protected void deleteObject(int uniqueId) {}
}
