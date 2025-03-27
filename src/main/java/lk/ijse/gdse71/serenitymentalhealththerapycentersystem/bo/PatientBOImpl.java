package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.PatientDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.PatientDAOImpl;

import java.util.Optional;

public class PatientBOImpl implements PatientBO {
    PatientDAO patientDAO = new PatientDAOImpl();

    @Override
    public String getNextPatientId() {
        return patientDAO.getNextId();
    }
}
