package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class LoginPageController {

    @FXML
    private Button btnForgetPw;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnShowPw;

    @FXML
    private Button btnSignup;

    @FXML
    private Text lblLoginPageTopic;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblShown;

    @FXML
    private Label lblUserName;

    @FXML
    private AnchorPane loginPageAnchorPane;

    @FXML
    private VBox loginVBox;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void BtnSignupOnAction(ActionEvent event) {
        navigateTo("/view/signup-page.fxml");
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {

    }

    @FXML
    void clickBtnShowPassword(ActionEvent event) {

    }

    @FXML
    void forgetPwOnAction(ActionEvent event) {

    }

    public void navigateTo(String fxmlPath) {
        try {
            loginPageAnchorPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            loginPageAnchorPane.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

}
