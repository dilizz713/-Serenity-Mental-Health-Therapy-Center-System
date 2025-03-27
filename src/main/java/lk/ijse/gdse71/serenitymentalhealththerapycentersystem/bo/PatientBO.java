package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PatientDTO;

import java.util.Optional;

public interface PatientBO extends SuperBO{
   String getNextPatientId();

    boolean savePatient(PatientDTO patientDTO);
}
