package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.exception.DuplicateException;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.exception.NotFoundException;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config.FactoryConfiguration;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapistDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Patient;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Therapist> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<Therapist> query = session.createQuery("from Therapist ", Therapist.class);
        return query.list();
    }

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        String nextId = null;

        try {
            nextId = session
                    .createQuery("SELECT t.id FROM Therapist t ORDER BY t.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        if (nextId != null) {
            int newId = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("T%03d", newId);
        } else {
            return "T001";
        }
    }

    @Override
    public boolean save(Therapist therapist) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist existsTherapist = session.get(Therapist.class, therapist.getId());
            if(existsTherapist != null){
                throw new DuplicateException("Therapist already exists");
            }
            session.persist(therapist);
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
    public boolean update(Therapist therapist) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.merge(therapist);
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
    public boolean delete(String therapistId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Therapist therapist = session.get(Therapist.class,therapistId);
            if(therapist == null){
                throw new NotFoundException("Therapist not found");
            }

            session.remove(therapist);
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
    public List<Therapist> search(String searchText) {
        Session session = factoryConfiguration.getSession();
        List<Therapist> therapists;

        try{
            therapists = session.createQuery(
                            "FROM Therapist th WHERE " +
                                    "th.id LIKE :searchText OR " +
                                    "th.name LIKE :searchText OR " +
                                    "th.nic LIKE :searchText OR " +
                                    "CAST(th.mobileNumber AS string) LIKE :searchText",
                            Therapist.class)
                    .setParameter("searchText", "%" + searchText + "%")
                    .getResultList();
        }finally {
            session.close();
        }
        return therapists;
    }


}
