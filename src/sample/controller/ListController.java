package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import sample.database.DatabaseHandler;
import sample.model.Task;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ListController {

    @FXML
    private JFXListView<Task> listTask;

    @FXML
    private JFXTextField listTaskField;

    @FXML
    private JFXTextField listDescriptionField;

    @FXML
    private JFXButton listSaveTaskButton;

    @FXML
    private ImageView refreshButton;

//    ObservableList<String> listview = FXCollections.observableArrayList("John" , "Paulo" ,"Thanh", "Meems" , "Robets");

    private ObservableList<Task> tasks;
    private ObservableList<Task> refreshTasks;

    private DatabaseHandler dbhandler;

    @FXML
    public void initialize() {
        tasks = FXCollections.observableArrayList();
        dbhandler = new DatabaseHandler();
        ResultSet resultSet = dbhandler.getTasksByUser(AdditemController.userId);

        try {
            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getInt("taskid"));
                task.setTask(resultSet.getString("task"));
                task.setDatecreated(resultSet.getTimestamp("datecreated"));
                task.setDescription(resultSet.getString("description"));
                tasks.addAll(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        listTask.setItems(tasks);
        listTask.setCellFactory(CellController -> new CellController());

        listSaveTaskButton.setOnAction(event -> {
            addNewTask();
        });

        refreshButton.setOnMouseClicked(event -> {
            try {
                refreshList();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void addNewTask() {
        if (!listTaskField.getText().equals("") || !listDescriptionField.getText().equals("")) {
            Task myNewTask = new Task();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());

            myNewTask.setUserId(AdditemController.userId);
            myNewTask.setTask(listTaskField.getText().trim());
            myNewTask.setDescription(listDescriptionField.getText().trim());
            myNewTask.setDatecreated(timestamp);
            dbhandler.addTask(myNewTask);

            listTaskField.setText("");
            listDescriptionField.setText("");

            try {
                initialize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshList() throws SQLException {
        System.out.println("refreshList in ListCont Called");

        refreshTasks = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getTasksByUser(AdditemController.userId);

        while (resultSet.next()) {
            Task task = new Task();
            task.setTaskId(resultSet.getInt("taskid"));
            task.setTask(resultSet.getString("task"));
            task.setDatecreated(resultSet.getTimestamp("datecreated"));
            task.setDescription(resultSet.getString("description"));
            tasks.addAll(task);
        }
    }
}