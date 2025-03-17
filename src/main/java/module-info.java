module lk.ijse.gdse71.serenitymentalhealththerapycentersystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires lombok;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens lk.ijse.gdse71.serenitymentalhealththerapycentersystem to javafx.fxml;
    exports lk.ijse.gdse71.serenitymentalhealththerapycentersystem;

}