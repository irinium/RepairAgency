package ua.kiev.repairagency.dao;

import ua.kiev.repairagency.entity.appliance.ApplianceEntity;

public interface ApplianceDao extends GenericDao<ApplianceEntity, Long> {

    void update(ApplianceEntity entity, String param);

    int getNumberOfRows();

    int getLastInsertedId();
}
