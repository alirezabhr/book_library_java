package controller;

import constant.MyConst;
import controller.adaptors.*;
import controller.configs.BaseConfig;

import controller.file_stream.AppendableObjectOutputStream;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.rmi.server.ExportException;

public class utils {
    public static Adaptor getAdaptor(BaseConfig config) {
        if (config.getRecordMode().equals("Fix")) {
            if (config.getStringMode().equals("Fix")) {
                return new FixRecFixStr();
            } else {
                return new FixRecDynStr();
            }
        } else {
            if (config.getStringMode().equals("Fix")) {
                return new DynRecFixStr();
            } else {
                return new DynRecDynStr();
            }
        }
    }

    public static boolean isAnyEmptyField(TextField... textFields) {
        for (TextField textField :
                textFields) {
            if (textField.getText().equals("")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIntNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean isLongNumber(String number) {
        try {
            Long.parseLong(number);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean isDoubleNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public static void getAllData() throws Exception {
        File studentDatabase = new File(MyConst.constStudentDatabaseFilePathName);
        if (studentDatabase.exists()) {
            studentDatabase.createNewFile();
        }

        File bookDatabase = new File(MyConst.constBookDatabaseFilePathName);
        if (!bookDatabase.exists()) {
            bookDatabase.createNewFile();
        }

        File recordDatabase = new File(MyConst.constRecordDatabaseFilePathName);
        if (!recordDatabase.exists()) {
            recordDatabase.createNewFile();
        }

        File studentConfig = new File(MyConst.constStudentConfigFilePathName);
        if (!studentConfig.exists()) {
            studentConfig.createNewFile();
            throw new Exception("Student Config Is Empty");
        }

        File bookConfig = new File(MyConst.constBookConfigFilePathName);
        if (!bookConfig.exists()) {
            bookConfig.createNewFile();
            throw new Exception("Book Config Is Empty");
        }

        File recordConfig = new File(MyConst.constRecordConfigFilePathName);
        if (!recordConfig.exists()) {
            recordConfig.createNewFile();
            throw new Exception("Record Config Is Empty");
        }
    }
}
