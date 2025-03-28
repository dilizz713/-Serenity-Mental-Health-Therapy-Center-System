package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm.RegistrationTM;

import java.time.LocalDate;

public class RegistrationController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox cmbPatient;

    @FXML
    private ComboBox cmbProgram;

    @FXML
    private TableColumn<RegistrationTM, Double> colAdvancePayment;

    @FXML
    private TableColumn<RegistrationTM, LocalDate> colDate;

    @FXML
    private TableColumn<RegistrationTM, String> colPatient;

    @FXML
    private TableColumn<RegistrationTM, String> colPatientId;

    @FXML
    private TableColumn<RegistrationTM, String> colProgram;

    @FXML
    private TableColumn<RegistrationTM, String> colProgramId;

    @FXML
    private TableColumn<RegistrationTM, String> colRegId;

    @FXML
    private Label lblAdvancePayment;

    @FXML
    private Label lblAutoGeneratedId;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblPatient;

    @FXML
    private Label lblProgram;

    @FXML
    private AnchorPane registrationAnchorPane;

    @FXML
    private TableView<RegistrationTM> registrationTable;

    @FXML
    private TextField txtAdvancePayment;

    @FXML
    private TextField txtSearch;

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void resetBtnOnAction(ActionEvent event) {

    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

    }

}
