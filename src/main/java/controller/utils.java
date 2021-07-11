package controller;

import controller.adaptors.*;
import controller.configs.BaseConfig;

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
}
