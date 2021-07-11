package controller.adaptors;

import model.entities.Entity;

public abstract class DynamicRecordAdaptor extends Adaptor{
    public boolean isValidRecord(Entity entity) {
        return true;
    }
}
