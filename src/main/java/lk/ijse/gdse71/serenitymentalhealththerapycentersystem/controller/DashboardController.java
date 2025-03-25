package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane dashboardAnchorPane;

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private HBox navBar;

    @FXML
    private Button navBarBtnPatient;

    @FXML
    private Button navBarBtnPayment;

    @FXML
    private Button navBarBtnPrograms;

    @FXML
    private Button navBarBtnRegistration;

    @FXML
    private Button navBarBtnSession;

    @FXML
    private Button navBarBtnTherapist;

    @FXML
    private Button navBarBtnUSer;

    @FXML
    void navigateToPamentPage(ActionEvent event) {
        navigateTo("/view/payment.fxml");
    }

    @FXML
    void navigateToPatientPage(ActionEvent event) {
        navigateTo("/view/patient.fxml");
    }

    @FXML
    void navigateToProgramsPage(ActionEvent event) {
        navigateTo("/view/programs.fxml");
    }

    @FXML
    void navigateToRegistrationPage(ActionEvent event) {
        navigateTo("/view/registration.fxml");
    }

    @FXML
    void navigateToSessionsPage(ActionEvent event) {
        navigateTo("/view/session.fxml");
    }

    @FXML
    void navigateToTherapistPage(ActionEvent event) {
        navigateTo("/view/therapist.fxml");
    }

    @FXML
    void navigateToUserPage(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/patient.fxml");
    }

    public void navigateTo(String fxmlPath){
        try{
            homeAnchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            homeAnchorPane.getChildren().add(load);
        }catch (IOException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }
}
