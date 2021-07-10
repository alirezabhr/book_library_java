package model.configs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class BaseConfig {
    protected String recordMode;
    protected String stringMode;
    protected int recordSize = 0;

    // getters
    public String getRecordMode() {
        return recordMode;
    }
    public String getStringMode() {
        return stringMode;
    }
    public int getRecordSize() {
        return recordSize;
    }

    // abstract methods
//    public abstract boolean isFixFixValid();  //todo check if we need this method
    protected abstract void setFields(final String field, final String value) throws Exception;

    // normal methods
    protected void createConfig(String fileName) throws Exception {
        ArrayList<String> fileData = readConfigFile(fileName);
        for (String data : fileData) {
            String[] tokens = tokenize(data);
            String key = tokens[0];
            String value = tokens[1];
            setFields(key, value);
        }
    }
    protected void setBaseFields(final String field, final String value) throws Exception {
        switch (field) {
            case "RECORD_MODE":
                if (isModeKeyValid(field) && isModeValueValid(value)) {
                    this.recordMode = value;
                } else {
                    throw new Exception("record mode key or value is not valid");
                }
                break;
            case "STRING_MODE":
                if (isModeKeyValid(field) && isModeValueValid(value)) {
                    this.recordMode = value;
                } else {
                    throw new Exception("string mode key or value is not valid");
                }
                break;
            case "RECORD_SIZE":
                if (isIntNumValid(value)) {
                    this.recordSize = Integer.parseInt(value);
                } else {
                    throw new Exception("record size is not valid");
                }
                break;
        }
    }
    protected String[] tokenize(String line) {
        return line.split(" = ");
    }
    protected ArrayList<String> readConfigFile(String fileName) {
        ArrayList<String> fileData = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                fileData.add(data);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.\nCan not open the file.");
            e.printStackTrace();
        }
        return fileData;
    }
    private boolean isModeKeyValid(String key) {
        return key.equals("RECORD_MODE") || key.equals("STRING_MODE");
    }
    private boolean isModeValueValid(String value) {
        return value.equals("Fix") || value.equals("Dyn");
    }
    protected boolean isIntNumValid(String value) throws Exception {
        try{
            int number = Integer.parseInt(value);
            if (number < 0) {
                throw new Exception("number should be positive");
            }
            return true;
        }
        catch (NumberFormatException ex){
            System.out.println("Wrong Input Number");
            throw new Exception("input number is not valid");
        }
    }
}
