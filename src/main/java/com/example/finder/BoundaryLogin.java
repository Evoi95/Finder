package com.example.finder;

import control.ControllerLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BoundaryLogin {
    @FXML
    private Label userLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label toolBoxLabel;
    @FXML
    private Button loginButton;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private AnchorPane loginPane;

    private ControllerLogin cL;

    public void initialize(URL location, ResourceBundle resources) {

       ControllerLogin cL = new ControllerLogin();

    }

    @FXML
    private void checkData()
    {
        String email;
        String password;

        email = emailTextField.getText();
        password= passwordTextField.getText();



    }



}
