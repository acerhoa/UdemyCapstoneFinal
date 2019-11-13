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

public class AdditemformController implements Initializable {
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

    private int userId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbhandler = new DatabaseHandler();
        additemButton.setOnAction(event -> {
            Task newtask = new Task();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
            String taskText =  additemTask.getText().trim();
            String taskDescription = additemDescription.getText().trim();

            if(!taskText.equals("") || !taskDescription.equals("")){
                System.out.println("User Id: " + AdditemController.userId);
                newtask.setUserId(AdditemController.userId);
                newtask.setDatecreated(timestamp);
                newtask.setDescription(taskDescription);
                newtask.setTask(taskText);
                dbhandler.addTask(newtask);
                System.out.println("Something Added");
            }else{
                System.out.println("Nothing Added");
            }


        });
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println(this.userId);
    }
}
