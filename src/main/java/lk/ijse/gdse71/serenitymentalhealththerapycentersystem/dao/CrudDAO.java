package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{

    String getNextId();
    boolean save(Patient patient);

    List<Patient> getAll();

    boolean update(Patient patient);

    boolean delete(String patientId);


    List<Patient> search(String searchText);
}
