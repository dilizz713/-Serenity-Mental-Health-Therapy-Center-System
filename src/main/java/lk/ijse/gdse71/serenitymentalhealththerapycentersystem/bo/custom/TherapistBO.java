package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapistDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapyProgramDTO;

import java.util.ArrayList;
import java.util.List;

public interface TherapistBO extends SuperBO{
    ArrayList<TherapistDTO> getAllTherapist();

    String getNextTherapistId();


    boolean saveTherapist(TherapistDTO therapistDTO);

    boolean updateTherapist(TherapistDTO therapistDTO);

    boolean deleteTherapist(String therapistId);

    List<TherapistDTO> searchTherapist(String searchText);
}
