package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.PatientDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.PatientDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PatientDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = new PatientDAOImpl();

    @Override
    public String getNextPatientId() {
        return patientDAO.getNextId();
    }

    @Override
    public boolean savePatient(PatientDTO patientDTO) {
        return patientDAO.save(new Patient(
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getAddress(),
                patientDTO.getEmail(),
                patientDTO.getMobileNumber(),
                patientDTO.getNic(),
                patientDTO.getGender()
        ));
    }

    @Override
    public ArrayList<PatientDTO> getAllPatients() {
        List<Patient> patients = patientDAO.getAll();
        ArrayList<PatientDTO> patientDTOs = new ArrayList<>();

        for (Patient patient : patients) {
            patientDTOs.add(new PatientDTO(
                    patient.getId(),
                    patient.getName(),
                    patient.getAddress(),
                    patient.getEmail(),
                    patient.getMobileNumber(),
                    patient.getNic(),
                    patient.getGender()
            ));
        }
        return patientDTOs;

    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) {
        return patientDAO.update(new Patient(
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getAddress(),
                patientDTO.getEmail(),
                patientDTO.getMobileNumber(),
                patientDTO.getNic(),
                patientDTO.getGender()
        ));
    }

    @Override
    public boolean deletePatient(String patientId) {
        return patientDAO.delete(patientId);
    }

    @Override
    public List<PatientDTO> searchPatient(String searchText) {
        List<Patient> patients = patientDAO.search(searchText);
        ArrayList<PatientDTO> patientDTOs = new ArrayList<>();

        for (Patient patient : patients) {
            patientDTOs.add(new PatientDTO(
                    patient.getId(),
                    patient.getName(),
                    patient.getAddress(),
                    patient.getEmail(),
                    patient.getMobileNumber(),
                    patient.getNic(),
                    patient.getGender()
            ));
        }
        return patientDTOs;
    }


}
