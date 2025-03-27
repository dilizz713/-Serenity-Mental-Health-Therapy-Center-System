package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.exception.DuplicateException;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.exception.NotFoundException;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config.FactoryConfiguration;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.PatientDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDAOImpl implements PatientDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        String nextId = null;

        try{
            nextId = session
                    .createQuery("SELECT p.id FROM Patient p ORDER BY p.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            session.close();
        }

        if(nextId != null){
            int newId = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("P%03d", newId);
        }else{
            return "P001";
        }

    }

    @Override
    public boolean save(Patient patient) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Patient existsPatient = session.get(Patient.class, patient.getId());
            if(existsPatient != null){
                throw new DuplicateException("Patient already exists");
            }
            session.persist(patient);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public List<Patient> getAll() {
        Session session = factoryConfiguration.getSession();
       Query<Patient> query = session.createQuery("from Patient", Patient.class);
       return query.list();

    }

    @Override
    public boolean update(Patient patient) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.merge(patient);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String patientId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Patient patient = session.get(Patient.class,patientId);
            if(patient == null){
                throw new NotFoundException("Patient not found");
            }

            session.remove(patient);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    @Override
    public List<Patient> search(String searchText) {
        Session session = factoryConfiguration.getSession();
        List<Patient> patients;

        try{
            patients = session.createQuery(
                            "FROM Patient p WHERE " +
                                    "p.id LIKE :searchText OR " +
                                    "p.name LIKE :searchText OR " +
                                    "p.nic LIKE :searchText OR " +
                                    "CAST(p.mobileNumber AS string) LIKE :searchText",
                            Patient.class)
                    .setParameter("searchText", "%" + searchText + "%")
                    .getResultList();
        }finally {
            session.close();
        }
        return patients;
    }
}
