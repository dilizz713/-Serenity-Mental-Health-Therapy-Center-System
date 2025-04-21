package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;

public interface TherapyProgramDAO {
    List<TherapyProgram> getAll();

    boolean save(TherapyProgram therapyProgram);

    boolean update(TherapyProgram therapyProgram);

    boolean delete(String programId);

    String getNextId();

    List<TherapyProgram> search(String searchText);

    TherapyProgram getProgramId(String programId);

    ArrayList<String> getAllProgramNames();


    String getProgramIdByName(String selectedProgramName);

    String getProgramNameById(String programId);
}
