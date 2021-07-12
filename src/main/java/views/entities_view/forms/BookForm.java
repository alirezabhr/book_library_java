package views.entities_view.forms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.entities.Book;
import views.entities_view.widgets.CustomTextField;
import views.entities_view.widgets.MessageLabel;
import views.entities_view.widgets.TitleLabel;

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
    public VBox editFormDetail() {
        return null;
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
    protected void editObject(int uniqueId, Object... objects) {}
    @Override
    protected void deleteObject(int uniqueId) {}
}
