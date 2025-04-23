package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.exception.NotFoundException;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config.FactoryConfiguration;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.TherapySessionDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapySessionDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Payment;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Therapist;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class TherapySessionDAOImpl implements TherapySessionDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        String nextId = null;

        try {
            nextId = session
                    .createQuery("SELECT ts.id FROM TherapySession ts ORDER BY ts.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        if (nextId != null) {
            int newId = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("S%03d", newId);
        } else {
            return "S001";
        }
    }

    @Override
    public boolean save(TherapySession entity) {
        return false;
    }

    @Override
    public List<TherapySession> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<TherapySession> query = session.createQuery("from TherapySession ", TherapySession.class);
        return query.list();
    }

    @Override
    public boolean update(TherapySession entity) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.merge(entity);
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
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            TherapySession therapySession = session.get(TherapySession.class,id);
            if(therapySession == null){
                throw new NotFoundException("Session not found");
            }
            session.remove(therapySession);
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
    public List<TherapySession> search(String searchText) {
        return List.of();
    }

    @Override
    public Optional<TherapySession> findByPK(String sessionId) {
        Session session = factoryConfiguration.getSession();
        TherapySession therapySession = session.get(TherapySession.class, sessionId);
        return Optional.ofNullable(therapySession);
    }

    @Override
    public boolean saveSessionWithPayment(Session session, TherapySession therapySession) {
        try {
            session.merge(therapySession);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public TherapySessionDTO getSessionById(String sessionId) {
        return null;
    }
}
