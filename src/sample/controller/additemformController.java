package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import sample.database.DatabaseHandler;
import sample.model.Task;

import java.net.URL;
import java.util.Calendar;
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

    private DatabaseHandler dbhandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbhandler = new DatabaseHandler();
        additemButton.setOnAction(event -> {
            Task newtask = new Task();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
            newtask.setDatecreated(timestamp);
            newtask.setDescription("A task");
            newtask.setTask("New Task");
            dbhandler.addTask(newtask);
        });
    }
}
