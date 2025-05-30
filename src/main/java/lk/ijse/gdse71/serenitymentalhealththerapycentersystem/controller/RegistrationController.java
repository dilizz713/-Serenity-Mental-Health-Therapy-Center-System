package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.BOFactory;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.PatientBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.ProgramBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.RegistrationBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl.PatientBOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl.ProgramBOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl.RegistrationBOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.RegistrationDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.TherapistDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm.RegistrationTM;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm.TherapistTM;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RegistrationController implements Initializable {

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
    private Label lblPatientId;

    @FXML
    private Label lblProgram;

    @FXML
    private Label lblProgramId;

    @FXML
    private AnchorPane registrationAnchorPane;

    @FXML
    private TableView<RegistrationTM> registrationTable;

    @FXML
    private TextField txtAdvancePayment;

    @FXML
    private TextField txtSearch;

    ProgramBO programBO = (ProgramBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PROGRAM);
    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.REGISTRATION);
    PatientBO patientBO = (PatientBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.PATIENT);

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        String regId = lblAutoGeneratedId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this record?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if(optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = registrationBO.deleteDetails(regId);
            if(isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Record deleted Successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to delete this record").show();
            }
        }
    }

    @FXML
    void resetBtnOnAction(ActionEvent event) {
        refreshPage();
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String id = lblAutoGeneratedId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());

       String advance = txtAdvancePayment.getText();

        String programName = (String) cmbProgram.getValue();
        String programId = lblProgramId.getText();

        String patientName = (String) cmbPatient.getValue();
        String patientId = lblPatientId.getText();

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: black; -fx-background-color: white;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: black; -fx-background-color: white;";

        double advancePayment = -1;
        try {
            advancePayment = Double.parseDouble(advance);
            txtAdvancePayment.setStyle(defaultStyle);
        } catch (NumberFormatException e) {
            txtAdvancePayment.setStyle(errorStyle);
            errorMessage.append("- Advance Payment is empty or not a valid number\n");
            hasErrors = true;
        }

        if(programName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a program").show();
            return;
        }
        if(patientName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a patient").show();
            return;
        }


        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        boolean isSaved = registrationBO.saveRegistration(new RegistrationDTO(id ,patientId , programId , date , advancePayment ));
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Registration saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save registration!").show();
        }
    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {
        String id = lblAutoGeneratedId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());

        String advance = txtAdvancePayment.getText();

        String programName = (String) cmbProgram.getValue();
        String programId = lblProgramId.getText();

        String patientName = (String) cmbPatient.getValue();
        String patientId = lblPatientId.getText();

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: black; -fx-background-color: white;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: black; -fx-background-color: white;";

        double advancePayment = -1;
        try {
            advancePayment = Double.parseDouble(advance);
            txtAdvancePayment.setStyle(defaultStyle);
        } catch (NumberFormatException e) {
            txtAdvancePayment.setStyle(errorStyle);
            errorMessage.append("- Advance Payment is empty or not a valid number\n");
            hasErrors = true;
        }

        if(programName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a program").show();
            return;
        }
        if(patientName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a patient").show();
            return;
        }


        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        boolean isUpdated = registrationBO.updateRegistration(new RegistrationDTO(id ,patientId , programId , date , advancePayment ));
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Registration updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update registration!").show();
        }
    }

    @FXML
    void selectPatient(ActionEvent event) {
        try{
            String selectedPatientName = (String) cmbPatient.getSelectionModel().getSelectedItem();
            if(selectedPatientName != null) {
                String patientId = patientBO.getPatientIdByName(selectedPatientName);
                lblPatientId.setText(patientId);
            }
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load program id").show();
        }
    }

    @FXML
    void selectProgram(ActionEvent event) {
        try{
            String selectedProgramName = (String) cmbProgram.getSelectionModel().getSelectedItem();
            if(selectedProgramName != null) {
                String programId = programBO.getProgramIdByName(selectedProgramName);
                lblProgramId.setText(programId);
            }
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load program id").show();
        }
    }

    @FXML
    void clickedTable(MouseEvent event) {
        RegistrationTM registrationTM = registrationTable.getSelectionModel().getSelectedItem();
        if (registrationTM != null) {
            lblAutoGeneratedId.setText(registrationTM.getRegistrationId());
            lblPatientId.setText(registrationTM.getPatientId());
            lblProgramId.setText(registrationTM.getProgramId());
            cmbPatient.setValue(registrationTM.getPatient());
            cmbProgram.setValue(registrationTM.getProgram());
            txtAdvancePayment.setText(String.valueOf(registrationTM.getAdvancePayment()));
            lblDate.setText(String.valueOf(registrationTM.getDate()));
        }

        btnSave.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRegId.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colAdvancePayment.setCellValueFactory(new PropertyValueFactory<>("advancePayment"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        lblDate.setText(LocalDate.now().toString());

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        cmbPatient.setStyle(defaultStyle);
        cmbProgram.setStyle(defaultStyle);
        txtAdvancePayment.setStyle(defaultStyle);

        txtSearch.setOnAction(event -> {
            try {
                searchRegistrationDetails();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error searching details").show();
            }
        });

        try {
            loadPatientNames();
            loadProgramNames();
            refreshPage();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load registration id").show();
        }
    }

    private void refreshPage() {
        loadNextRegistrationId();
        loadTableData();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        txtAdvancePayment.setText("");
        cmbProgram.getSelectionModel().clearSelection();
        cmbPatient.getSelectionModel().clearSelection();

        cmbProgram.setValue(null);
        cmbPatient.setValue(null);

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        txtAdvancePayment.setStyle(defaultStyle);
        cmbProgram.setStyle(defaultStyle);
        cmbPatient.setStyle(defaultStyle);
    }

    private void loadTableData() {
        ArrayList<RegistrationDTO> registrationDTOS = registrationBO.getAllRegistrations();
        ObservableList<RegistrationTM> registrationTMS = FXCollections.observableArrayList();

        for (RegistrationDTO registrationDTO : registrationDTOS) {

            String patientName = patientBO.getPatientNameById(registrationDTO.getPatientId());
            String programName = programBO.getProgramNameById(registrationDTO.getProgramId());

            RegistrationTM registrationTM = new RegistrationTM(
                    registrationDTO.getId(),
                    registrationDTO.getPatientId(),
                    registrationDTO.getProgramId(),
                    patientName,
                    programName,
                    registrationDTO.getAdvancePayment(),
                    registrationDTO.getDate()

            );
            registrationTMS.add(registrationTM);
        }
        registrationTable.setItems(registrationTMS);
    }

    private void loadNextRegistrationId() {
        String nextRegistrationId = registrationBO.getNextRegistrationId();
        lblAutoGeneratedId.setText(nextRegistrationId);
    }

    private void loadProgramNames() {
        ArrayList<String> programNames = programBO.getAllProgramsNames();
        cmbProgram.setItems(FXCollections.observableArrayList(programNames));
    }

    private void loadPatientNames() {
        ArrayList<String> patientNames = patientBO.getAllPatientNames();
        cmbPatient.setItems(FXCollections.observableArrayList(patientNames));
    }

    private void searchRegistrationDetails() {
        String searchText = txtSearch.getText().trim();

        registrationBO.searchRecord(searchText);

        if(searchText.isEmpty()){
            loadTableData();
            return;
        }
        List<RegistrationDTO> registrationDTOS = registrationBO.searchRecord(searchText);
        ObservableList<RegistrationTM> registrationTMS = FXCollections.observableArrayList();

        for (RegistrationDTO registrationDTO : registrationDTOS) {

            String patientName = patientBO.getPatientNameById(registrationDTO.getPatientId());
            String programName = programBO.getProgramNameById(registrationDTO.getProgramId());

            RegistrationTM registrationTM = new RegistrationTM(
                    registrationDTO.getId(),
                    registrationDTO.getPatientId(),
                    registrationDTO.getProgramId(),
                    patientName,
                    programName,
                    registrationDTO.getAdvancePayment(),
                    registrationDTO.getDate()

            );
            registrationTMS.add(registrationTM);
        }
        registrationTable.setItems(registrationTMS);
    }
}
