package views.entities_view.forms;

import controller.binders.RecordBinder;
import controller.binders.StudentBinder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import model.CustomDate;
import model.entities.Entity;
import model.entities.Record;
import model.entities.Student;
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
    public VBox editFormDetail(Entity entity) {
        Record record = (Record) entity;
        int uniqueId = record.getUniqueId();
        CustomDate loanDate = CustomDate.intToDate(record.getIntLoanedDate());
        CustomDate returnDate = CustomDate.intToDate(record.getIntReturnDate());
        TitleLabel label = new TitleLabel("Edit Record");

        CustomTextField studentIdTextField = new CustomTextField("student id", record.getStudentId().toString());
        CustomTextField bookIdTextField = new CustomTextField("book id", record.getBookId().toString());

        Label loanDateLabel = new Label("Loan Date");
        CustomTextField loanYear = new CustomTextField("Y", loanDate.getYear().toString());
        loanYear.setMaxWidth(60);
        CustomTextField loanMonth = new CustomTextField("M", loanDate.getMonth().toString());
        loanMonth.setMaxWidth(30);
        CustomTextField loanDay = new CustomTextField("D", loanDate.getDay().toString());
        loanDay.setMaxWidth(30);
        Label returnDateLabel = new Label("Return Date");
        CustomTextField returnYear = new CustomTextField("Y", returnDate.getYear().toString());
        returnYear.setMaxWidth(60);
        CustomTextField returnMonth = new CustomTextField("M", returnDate.getMonth().toString());
        returnMonth.setMaxWidth(30);
        CustomTextField returnDay = new CustomTextField("D", returnDate.getDay().toString());
        returnDay.setMaxWidth(30);

        HBox loanDateRow = new HBox(loanDateLabel, loanYear, loanMonth, loanDay);
        HBox returnDateRow = new HBox(returnDateLabel, returnYear, returnMonth, returnDay);
        loanDateRow.setSpacing(10);
        returnDateRow.setSpacing(10);

        MessageLabel errorLabel = new MessageLabel("", "red");
        MessageLabel successLabel = new MessageLabel("", "green");

        Button button = new Button("Edit");
        button.setOnAction(event -> {
            errorLabel.setText("");
            successLabel.setText("");
            if (!isAnyEmptyField(studentIdTextField, bookIdTextField, loanYear, loanMonth, loanDay, returnYear, returnMonth, returnDay)) {
                String validation = checkRecordValidation(studentIdTextField.getText(), bookIdTextField.getText(), loanYear.getText(), loanMonth.getText(), loanDay.getText(), returnYear.getText(), returnMonth.getText(), returnDay.getText());
                if (validation.equals("valid")) {
                    this.editObject(uniqueId, studentIdTextField.getText(), bookIdTextField.getText(), loanYear.getText(), loanMonth.getText(), loanDay.getText(), returnYear.getText(), returnMonth.getText(), returnDay.getText());
                    successLabel.setText("Record Edited Successfully.");
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
    public VBox deleteFormDetail() {
        return null;
    }

    @Override
    protected void createObject(Object... objects) {
        int stdId = Integer.parseInt((String) objects[0]);
        int bookId = Integer.parseInt((String) objects[1]);
        int ly = Integer.parseInt((String) objects[2]);
        int lm = Integer.parseInt((String) objects[3]);
        int ld = Integer.parseInt((String) objects[4]);
        int ry = Integer.parseInt((String) objects[5]);
        int rm = Integer.parseInt((String) objects[6]);
        int rd = Integer.parseInt((String) objects[7]);

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
    protected void editObject(int uniqueId, Object... objects) {
        try {
            RecordBinder.editRecord(uniqueId, objects);
        } catch (Exception exception) {
            System.out.println("Can Not Edit A Record!!!");
        }
    }
    @Override
    protected void deleteObject(int uniqueId) {}
}
