package controller;

import controller.adaptors.*;
import controller.configs.BaseConfig;

import javafx.scene.control.TextField;

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
}
