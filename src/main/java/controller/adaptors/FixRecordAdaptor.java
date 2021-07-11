package controller.adaptors;

import model.entities.Entity;

public abstract class FixRecordAdaptor extends Adaptor{
    public boolean isValidRecord(Entity entity) {
        return entity.getBaseConfig().isValidRecord(entity.getAllFields());
    }
}
