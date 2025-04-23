package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PaymentDTO;

import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO{
    String getNextPaymentId();

    ArrayList<PaymentDTO> getAllPayments();

    List<PaymentDTO> getPaymentsByPatientAndProgram(String patientId, String programId);
}
