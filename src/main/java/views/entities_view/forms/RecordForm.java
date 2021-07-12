package views.entities_view.forms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import model.CustomDate;
import model.entities.Record;
import views.entities_view.widgets.CustomTextField;
import views.entities_view.widgets.MessageLabel;
import views.entities_view.widgets.TitleLabel;

import static controller.binders.RecordBinder.checkRecordValidation;
import static controller.binders.RecordBinder.getRecord;
import static controller.utils.isAnyEmptyField;

public class RecordForm extends BaseForm{
    @Override
    public VBox createFormDetail() {
        TitleLabel label = new TitleLabel("Create Record");

        CustomTextField studentIdTextField = new CustomTextField("student id");
        CustomTextField bookIdTextField = new CustomTextField("book id");
        Label loanDateLabel = new Label("Loan Date");
        CustomTextField loanYear = new CustomTextField("Y");
        loanYear.setMaxWidth(60);
        CustomTextField loanMonth = new CustomTextField("M");
        loanMonth.setMaxWidth(30);
        CustomTextField loanDay = new CustomTextField("D");
        loanDay.setMaxWidth(30);
        Label returnDateLabel = new Label("Return Date");
        CustomTextField returnYear = new CustomTextField("Y");
        returnYear.setMaxWidth(60);
        CustomTextField returnMonth = new CustomTextField("M");
        returnMonth.setMaxWidth(30);
        CustomTextField returnDay = new CustomTextField("D");
        returnDay.setMaxWidth(30);

        HBox loanDateRow = new HBox(loanDateLabel, loanYear, loanMonth, loanDay);
        HBox returnDateRow = new HBox(returnDateLabel, returnYear, returnMonth, returnDay);
        loanDateRow.setSpacing(10);
        returnDateRow.setSpacing(10);

        MessageLabel errorLabel = new MessageLabel("", "red");
        MessageLabel successLabel = new MessageLabel("", "green");

        Button button = new Button("Create");
        button.setOnAction(event -> {
            errorLabel.setText("");
            successLabel.setText("");
            if (!isAnyEmptyField(studentIdTextField, bookIdTextField, loanYear, loanMonth, loanDay, returnYear, returnMonth, returnDay)) {
                String validation = checkRecordValidation(studentIdTextField.getText(), bookIdTextField.getText(), loanYear.getText(), loanMonth.getText(), loanDay.getText(), returnYear.getText(), returnMonth.getText(), returnDay.getText());
                if (validation.equals("valid")) {
                    this.createObject(studentIdTextField.getText(), bookIdTextField.getText(), loanYear.getText(), loanMonth.getText(), loanDay.getText(), returnYear.getText(), returnMonth.getText(), returnDay.getText());
                    successLabel.setText("Record Created Successfully.");
                } else {
                    errorLabel.setText(validation);
                }
            } else {
                errorLabel.setText("You Have Empty Fields!");
            }
        });

        VBox root = new VBox(label, studentIdTextField, bookIdTextField, loanDateRow, returnDateRow, button, errorLabel, successLabel);
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
        int stdId = (Integer) objects[0];
        int bookId = (Integer) objects[1];
        int ly = (Integer) objects[2];
        int lm = (Integer) objects[3];
        int ld = (Integer) objects[4];
        int ry = (Integer) objects[5];
        int rm = (Integer) objects[6];
        int rd = (Integer) objects[7];

        CustomDate loanDate = new CustomDate(ly, lm, ld);
        CustomDate returnDate = new CustomDate(ry, rm, rd);

        Record record = getRecord(stdId, bookId, loanDate, returnDate);
        try {
            record.create();
        } catch (Exception exception) {
            System.out.println("Can Not Create A Record!!!");
        }
    }
    @Override
    protected void editObject() {}
    @Override
    protected void deleteObject() {}
}
