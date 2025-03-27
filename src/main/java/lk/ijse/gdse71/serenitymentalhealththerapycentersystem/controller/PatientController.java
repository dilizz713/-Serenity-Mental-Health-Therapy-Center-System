package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.PatientBO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.PatientBOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.PatientDAO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.PatientDAOImpl;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.PatientDTO;
import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dto.tm.PatientTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PatientTM, String> colAddress;

    @FXML
    private TableColumn<PatientTM, String> colEmail;

    @FXML
    private TableColumn<PatientTM, String> colGender;

    @FXML
    private TableColumn<PatientTM, Integer> colMobile;

    @FXML
    private TableColumn<PatientTM, String> colNic;

    @FXML
    private TableColumn<PatientTM, String> colPatientId;

    @FXML
    private TableColumn<PatientTM, String> colPatientName;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblAutoGeneratedId;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblName;

    @FXML
    private Label lblNic;

    @FXML
    private AnchorPane patientAnchorPane;

    @FXML
    private TableView<PatientTM> patientTable;

    @FXML
    private RadioButton rdbFemale;

    @FXML
    private RadioButton rdbMale;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtSearch;

    PatientBO patientBO = new PatientBOImpl();

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void resetBtnOnAction(ActionEvent event) {

    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        String id = lblAutoGeneratedId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]$|^[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: white; -fx-background-color: transparent;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: white; -fx-background-color: transparent;";


        if (name.isEmpty() || !name.matches(namePattern)) {
            txtName.setStyle(errorStyle);
            errorMessage.append("- Name is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            txtName.setStyle(defaultStyle);
        }
        if (address.isEmpty()) {
            txtAddress.setStyle(errorStyle);
            errorMessage.append("- Address is empty\n");
            hasErrors = true;

        }else{
            txtAddress.setStyle(defaultStyle);
        }
        if (email.isEmpty() || !email.matches(emailPattern)) {
            txtEmail.setStyle(errorStyle);
            errorMessage.append("- Email is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            txtEmail.setStyle(defaultStyle);
        }

        int phone = -1;
        try {
            phone = Integer.parseInt(mobile);
            txtMobile.setStyle(defaultStyle);
        } catch (NumberFormatException e) {
            txtMobile.setStyle(errorStyle);
            errorMessage.append("- Phone number is empty or not a valid number\n");
            hasErrors = true;
        }

        if (nic.isEmpty() || !nic.matches(nicPattern)) {
            txtNic.setStyle(errorStyle);
            errorMessage.append("- NIC is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            txtNic.setStyle(defaultStyle);
        }


        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }




    }

    @FXML
    void selectFemaleBtn(ActionEvent event) {

    }

    @FXML
    void selectMaleBtn(ActionEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        String defaultStyle = "-fx-border-color: black; -fx-text-fill: white; -fx-background-color: transparent;";

        txtName.setStyle(defaultStyle);
        txtAddress.setStyle(defaultStyle);
        txtEmail.setStyle(defaultStyle);
        txtMobile.setStyle(defaultStyle);
        txtNic.setStyle(defaultStyle);

        txtSearch.setOnAction(event ->{
            try{
                searchCustomers();
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error searching patient").show();
            }
        });

        try{
            refreshPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load patient id").show();
        }
    }

    private void refreshPage() {
        loadNextPatientId();
        loadTableData();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        txtName.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtMobile.setText("");
        txtNic.setText("");
    }

    private void loadTableData() {
    }

    private void loadNextPatientId() {
       String nextPatientId = patientBO.getNextPatientId();
       lblAutoGeneratedId.setText(nextPatientId);
    }

    private void searchCustomers() {

    }
}
