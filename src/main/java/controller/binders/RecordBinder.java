package controller.binders;

import controller.adaptors.Adaptor;
import controller.configs.LibraryRecordConfig;
import controller.utils;
import model.entities.Entity;
import model.entities.Record;

import java.util.ArrayList;

public class RecordBinder {
    private static Record createTmpObject() {
        LibraryRecordConfig config = new LibraryRecordConfig("./configs/record_config.txt");
        Adaptor adaptor = utils.getAdaptor(config);
        return new Record(adaptor, config);
    }
    public static ArrayList<Record> getAllStudents() {
        Record tmpRecord = createTmpObject();
        ArrayList<Entity> entities = tmpRecord.getAllObjects();

        ArrayList<Record> records = new ArrayList<>();
        for (Entity e : entities) {
            records.add((Record) e);
        }

        return records;
    }
}
