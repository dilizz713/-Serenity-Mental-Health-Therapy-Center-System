package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.PaymentBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.PaymentDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PaymentDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapistDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Payment;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.entity.Therapist;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = new PaymentDAOImpl();

    @Override
    public String getNextPaymentId() {
        return paymentDAO.getNextId();
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();

        for (Payment payment : payments) {

            paymentDTOS.add(new PaymentDTO(
                    payment.getId(),
                    payment.getDate(),
                    payment.getAmount(),
                    payment.getRemainingAmount(),
                    payment.getStatus(),
                    payment.getTherapySession().getId()
            ));
        }
        return paymentDTOS;
    }

    @Override
    public List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId) {
        return paymentDAO.getPaymentsByPatientAndProgram(patientId,programId);
    }
}
