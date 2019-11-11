package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class additemformController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField additemTask;

    @FXML
    private JFXTextField additemDescription;

    @FXML
    private JFXButton additemButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
