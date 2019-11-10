package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class signupController implements Initializable {
    @FXML
    private JFXTextField signupFirstname;

    @FXML
    private JFXTextField signupLastname;

    @FXML
    private JFXTextField signupUsername;

    @FXML
    private JFXTextField signupLocation;

    @FXML
    private JFXCheckBox signupCBmale;

    @FXML
    private JFXCheckBox signupCBfemale;

    @FXML
    private JFXPasswordField signupPassword;

    @FXML
    private JFXButton signupButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
