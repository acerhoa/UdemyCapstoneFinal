package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private JFXTextField login_user;

    @FXML
    private JFXPasswordField login_pass;

    @FXML
    private JFXButton login_loginButton;

    @FXML
    private JFXButton login_signUpButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_loginButton.setOnAction(event ->{
            System.out.println("Button CLicked");
        });
    }
}
