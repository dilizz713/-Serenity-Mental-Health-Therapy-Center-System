package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;

public interface CrudDAO<T> extends SuperDAO{

    String getNextId();
    boolean save(Patient patient);
}
