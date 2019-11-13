package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Scanner;

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

    @FXML
    private Label successLabel;

    @FXML
    private JFXButton todosButton;

    private DatabaseHandler dbhandler;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dbhandler = new DatabaseHandler();

        additemButton.setOnAction(event -> {
            Task newtask = new Task();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
            String taskText = additemTask.getText().trim();
            String taskDescription = additemDescription.getText().trim();

            if (!taskText.equals("") || !taskDescription.equals("")) {
                System.out.println("User Id: " + AdditemController.userId);
                newtask.setUserId(AdditemController.userId);
                newtask.setDatecreated(timestamp);
                newtask.setDescription(taskDescription);
                newtask.setTask(taskText);
                dbhandler.addTask(newtask);
//                System.out.println("Something Added");
                successLabel.setVisible(true);
                todosButton.setVisible(true);
                int taskNumber = 0;
                try {
                    taskNumber = dbhandler.getAllTasks(AdditemController.userId);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                todosButton.setText("My Todo's: " + "(" + taskNumber + ")");

                additemTask.setText("");
                additemDescription.setText("");

                todosButton.setOnAction(event1 -> {
//                    send user to list screen
                    todosButton.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/view/list.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                });
            } else {
                System.out.println("Nothing Added");
            }

        });
    }


}
