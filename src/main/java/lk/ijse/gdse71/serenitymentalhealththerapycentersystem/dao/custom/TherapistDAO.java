package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Therapist;

import java.util.List;

public interface TherapistDAO {
    List<Therapist> getAll();

    String getNextId();


    boolean save(Therapist therapist);

    boolean update(Therapist therapist);

    boolean delete(String therapistId);

    List<Therapist> search(String searchText);
}
