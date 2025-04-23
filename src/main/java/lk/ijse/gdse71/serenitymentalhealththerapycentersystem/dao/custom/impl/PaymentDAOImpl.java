package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.config.FactoryConfiguration;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.PaymentDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PaymentDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Payment;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.TherapySession;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        String nextId = null;

        try {
            nextId = session
                    .createQuery("SELECT py.id FROM Payment py ORDER BY py.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();
        } finally {
            session.close();
        }

        if (nextId != null) {
            int newId = Integer.parseInt(nextId.substring(1)) + 1;
            return String.format("P%03d", newId);
        } else {
            return "P001";
        }
    }

    @Override
    public boolean save(Payment entity) {
        return false;
    }

    @Override
    public List<Payment> getAll() {
        return List.of();
    }

    @Override
    public boolean update(Payment entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Payment> search(String searchText) {
        return List.of();
    }

    @Override
    public boolean savePayment(Session session, Payment payment) {
        try{
            session.merge(payment);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Optional<Payment> findByPK(String paymentId) {
        Session session = factoryConfiguration.getSession();
        Payment payment = session.get(Payment.class, paymentId);
        return Optional.ofNullable(payment);
    }

    @Override
    public List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId) {
       Session session = factoryConfiguration.getSession();
       List<PaymentDTO> paymentDTOList = new ArrayList<>();

       try{
           session.beginTransaction();

           String hql =  "SELECT p FROM Payment p " +
                   "JOIN p.therapySession ts " +
                   "WHERE ts.patient.id = :patientId AND ts.therapyProgram.id = :programId";

           List<Payment> payments = session.createQuery(hql,Payment.class)
                   .setParameter("patientId",patientId)
                   .setParameter("programId",programId)
                   .getResultList();

           for(Payment payment : payments){
               PaymentDTO paymentDTO = new PaymentDTO(
                       payment.getId(),
                       payment.getDate(),
                       payment.getAmount(),
                       payment.getRemainingAmount(),
                       payment.getStatus(),
                       payment.getTherapySession().getId()
               );
               paymentDTOList.add(paymentDTO);
           }
           session.getTransaction().commit();
       }catch(Exception e){
           if(session.getTransaction() != null) session.getTransaction().rollback();
           e.printStackTrace();
       }finally{
           session.close();
       }
       return paymentDTOList;
    }
}
