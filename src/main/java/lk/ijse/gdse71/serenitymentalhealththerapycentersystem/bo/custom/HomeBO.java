package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom;

public interface HomeBO extends SuperBO {
    int getPatientCount();

    int getProgramCount();

    int getTherapistCount();

    int getSessionCountForToday();
}
