package controller.configs;

public class ConfigCreator {
    public static void setStudentConfigFile(String recMode, String strMode, Integer recSz, Integer nameSz, Integer lastNameSz) throws Exception {
        StudentConfig studentConfig = new StudentConfig();
        String recordMode;
        String stringMode;
        if (recMode.equals("Fix")) {
            recordMode = recMode;
        } else {
            recordMode = "Dyn";
        }
        if (strMode.equals("Fix")) {
            stringMode = strMode;
        } else {
            stringMode = "Dyn";
        }

        studentConfig.setRecordMode(recordMode);
        studentConfig.setStringMode(stringMode);
        studentConfig.setRecordSize(recSz);
        studentConfig.setNameSize(nameSz);
        studentConfig.setLastNameSize(lastNameSz);

        studentConfig.write();
    }
    public static void setBookConfigFile(String recMode, String strMode, Integer recSz, Integer nameSz, Integer authorSz, Integer publisherSz) throws Exception {
        BookConfig bookConfig = new BookConfig();
        String recordMode;
        String stringMode;
        if (recMode.equals("Fix")) {
            recordMode = recMode;
        } else {
            recordMode = "Dyn";
        }
        if (strMode.equals("Fix")) {
            stringMode = strMode;
        } else {
            stringMode = "Dyn";
        }

        bookConfig.setRecordMode(recordMode);
        bookConfig.setStringMode(stringMode);
        bookConfig.setRecordSize(recSz);
        bookConfig.setNameSize(nameSz);
        bookConfig.setAuthorSize(authorSz);
        bookConfig.setPublisherSize(publisherSz);

        bookConfig.write();
    }
    public static void setRecordConfigFile(String recMode, String strMode, Integer recSize) throws Exception {
        LibraryRecordConfig recordConfig = new LibraryRecordConfig();
        String recordMode;
        String stringMode;
        if (recMode.equals("Fix")) {
            recordMode = recMode;
        } else {
            recordMode = "Dyn";
        }
        if (strMode.equals("Fix")) {
            stringMode = strMode;
        } else {
            stringMode = "Dyn";
        }

        recordConfig.setRecordMode(recordMode);
        recordConfig.setStringMode(stringMode);
        recordConfig.setRecordSize(recSize);

        recordConfig.write();
    }
}
