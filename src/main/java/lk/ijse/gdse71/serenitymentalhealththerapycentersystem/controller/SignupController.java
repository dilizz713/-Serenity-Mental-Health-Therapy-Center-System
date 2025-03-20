package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SignupController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSignup;

    @FXML
    private Label lblConfirmPW;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblFName;

    @FXML
    private Label lblLName;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblTopicSignup;

    @FXML
    private Label lblUserName;

    @FXML
    private RadioButton radioBtnAdmin;

    @FXML
    private RadioButton rdBtnReceptionist;

    @FXML
    private AnchorPane signupAnchorPane;

    @FXML
    private VBox signupPageVBox;

    @FXML
    private TextField txtConfirmPW;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtPW;

    @FXML
    private TextField txtUserName;

    @FXML
    void backBtnOnAction(ActionEvent event) {
        navigateTo("/view/login-page.fxml");
    }

    @FXML
    void selectAdmin(ActionEvent event) {

    }

    @FXML
    void selectReceptionist(ActionEvent event) {

    }

    @FXML
    void signupOnAction(ActionEvent event) {

    }

    public void navigateTo(String fxmlPath) {
        try {
            signupAnchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            signupAnchorPane.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

}
