package controller.adaptors;

import controller.file_stream.AppendableObjectInputStream;
import controller.file_stream.AppendableObjectOutputStream;
import model.entities.Entity;

import java.io.IOException;

public abstract class FixRecordAdaptor extends Adaptor{
    public void writeRecord(Entity entity) throws IOException {

    }
    public Entity readRecord(Entity entity) throws IOException {
        return null;
    }
}
