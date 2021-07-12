package views.entities_view.forms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import model.entities.Student;
import static controller.binders.StudentBinder.checkStudentValidation;
import static controller.binders.StudentBinder.getStudent;
import static controller.utils.isAnyEmptyField;
import views.entities_view.widgets.CustomTextField;
import views.entities_view.widgets.MessageLabel;
import views.entities_view.widgets.TitleLabel;

public class StudentForm extends BaseForm{
    @Override
    public VBox createFormDetail() {
        TitleLabel label = new TitleLabel("Create Student");

        CustomTextField nameTextField = new CustomTextField("name");
        CustomTextField lastNameTextField = new CustomTextField("last name");
        CustomTextField stdIdTextField = new CustomTextField("student id");
        stdIdTextField.setMaxWidth(130);

        MessageLabel errorLabel = new MessageLabel("", "red");
        MessageLabel successLabel = new MessageLabel("", "green");

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
