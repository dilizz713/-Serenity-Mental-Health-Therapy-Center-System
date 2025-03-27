package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm;

import java.time.LocalDate;

public class PaymentTM {
    private String id;
    private String sessionId;
    private String patientName;
    private String program;
    private String description;
    private LocalDate date;
    private double amount;
    private double remainingAmount;
    private String status;
}
