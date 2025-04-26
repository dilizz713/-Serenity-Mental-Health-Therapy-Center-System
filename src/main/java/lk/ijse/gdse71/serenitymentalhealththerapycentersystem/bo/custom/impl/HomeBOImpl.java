package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.HomeBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.ProgramBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.PatientDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapistDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapySessionDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.PatientDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.TherapistDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.TherapyProgramDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.TherapySessionDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapySession;

public class HomeBOImpl implements HomeBO {

    PatientDAO patientDAO = new PatientDAOImpl();
    TherapyProgramDAO therapyProgramDAO = new TherapyProgramDAOImpl();
    TherapistDAO therapistDAO = new TherapistDAOImpl();
    TherapySessionDAO therapySessionDAO = new TherapySessionDAOImpl();

    @Override
    public int getPatientCount() {
       return patientDAO.getPatientCount();
    }

    @Override
    public int getProgramCount() {
        return therapyProgramDAO.getProgramCount();
    }

    @Override
    public int getTherapistCount() {
        return therapistDAO.getTherapistCount();
    }

    @Override
    public int getSessionCountForToday() {
        return therapySessionDAO.getSessionCountForToday();
    }
}
