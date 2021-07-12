package views.entities_view.forms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.entities.Student;

import static controller.binders.StudentBinder.checkStudentValidation;
import static controller.binders.StudentBinder.getStudent;
import static controller.utils.isAnyEmptyField;

public class StudentForm extends BaseForm{
    @Override
    public VBox createFormDetail() {
        Label label = new Label("Create Student");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        TextField nameTextField = new TextField();
        nameTextField.setFocusTraversable(false);
        TextField lastNameTextField = new TextField();
        lastNameTextField.setFocusTraversable(false);
        TextField stdIdTextField = new TextField();
        stdIdTextField.setFocusTraversable(false);


        nameTextField.setPromptText("name");
        lastNameTextField.setPromptText("last name");
        stdIdTextField.setPromptText("student id");

        nameTextField.setMaxWidth(150);
        lastNameTextField.setMaxWidth(150);
        stdIdTextField.setMaxWidth(130);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.valueOf("red"));

        Label successLabel = new Label();
        successLabel.setTextFill(Color.valueOf("green"));

        Button button = new Button("Create");
        button.setOnAction(event -> {
            errorLabel.setText("");
            successLabel.setText("");
            if (!isAnyEmptyField(nameTextField, lastNameTextField, stdIdTextField)) {
                String validation = checkStudentValidation(nameTextField.getText(), lastNameTextField.getText(), stdIdTextField.getText());
                if (validation.equals("valid")) {
                    this.createObject(nameTextField.getText(), lastNameTextField.getText(), Integer.parseInt(stdIdTextField.getText()));
                    successLabel.setText("Student Created Successfully.");
                } else {
                    errorLabel.setText(validation);
                }
            } else {
                errorLabel.setText("You Have Empty Fields!");
            }
        });

        VBox root = new VBox(label, nameTextField, lastNameTextField, stdIdTextField, button, errorLabel, successLabel);
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
        Student student = getStudent(objects);
        try {
            student.create();
        } catch (Exception exception) {
            System.out.println("Can Not Create A Student!!!");
        }
    }
    @Override
    protected void editObject() {}
    @Override
    protected void deleteObject() {}
}
