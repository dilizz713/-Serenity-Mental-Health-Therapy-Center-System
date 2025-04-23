package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.TherapySessionBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.PatientDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapistDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapyProgramDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapySessionDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.PatientDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.TherapistDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.TherapyProgramDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.TherapySessionDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapistDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapySessionDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Therapist;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapyProgram;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapySession;

import java.util.ArrayList;
import java.util.List;

public class TherapySessionBOImpl implements TherapySessionBO {
    TherapySessionDAO therapySessionDAO = new TherapySessionDAOImpl();
    TherapyProgramDAO therapyProgramDAO = new TherapyProgramDAOImpl();
    PatientDAO  patientDAO = new PatientDAOImpl();
    TherapistDAO   therapistDAO = new TherapistDAOImpl();

    @Override
    public String getNextSessionId() {
        return therapySessionDAO.getNextId();
    }

    @Override
    public ArrayList<TherapySessionDTO> getAllSessions() {
        List<TherapySession> therapySessions = therapySessionDAO.getAll();
        ArrayList<TherapySessionDTO> therapySessionDTOS = new ArrayList<>();

        for (TherapySession therapySession : therapySessions) {
            therapySessionDTOS.add(new TherapySessionDTO(
                    therapySession.getId(),
                    therapySession.getPatient().getId(),
                    therapySession.getTherapyProgram().getId(),
                    therapySession.getTherapist().getId(),
                    therapySession.getDescription(),
                    therapySession.getDate(),
                    therapySession.getSessionDate()
            ));
        }
        return therapySessionDTOS;
    }

    @Override
    public TherapySessionDTO getSessionById(String sessionId) {
        return therapySessionDAO.getSessionById(sessionId);
    }

    @Override
    public boolean updateSession(TherapySessionDTO therapySessionDTO) {
        TherapyProgram therapyProgram = therapyProgramDAO.getProgramId(therapySessionDTO.getProgramId());
        Patient patient = patientDAO.getPatientId(therapySessionDTO.getPatientId());
        Therapist  therapist = therapistDAO.getTherapistId(therapySessionDTO.getTherapistId());

        if(therapyProgram == null) {
            throw new RuntimeException("Program not found for ID: " + therapySessionDTO.getProgramId());
        }
        if(patient == null) {
            throw new RuntimeException("Patient not found for ID: " + therapySessionDTO.getPatientId());
        }
        if(therapist == null) {
            throw new RuntimeException("Therapist not found for ID: " + therapySessionDTO.getTherapistId());
        }

        TherapySession therapySession = new TherapySession(
                therapySessionDTO.getId(),
                therapySessionDTO.getDescription(),
                therapySessionDTO.getDate(),
                therapySessionDTO.getSessionDate(),
                patient,
                therapyProgram,
                therapist
        );
        return therapySessionDAO.update(therapySession);
    }

    @Override
    public boolean deleteSession(String sessionId) {
        return therapySessionDAO.delete(sessionId);
    }
}
