package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PatientDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PatientBO extends SuperBO{
   String getNextPatientId();

    boolean savePatient(PatientDTO patientDTO);

    ArrayList<PatientDTO> getAllPatients();

    boolean updatePatient(PatientDTO patientDTO);

    boolean deletePatient(String patientId);


 List<PatientDTO> searchPatient(String searchText);
}
