package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.animations.Shaker;
import sample.database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private DatabaseHandler dbhandler = new DatabaseHandler();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        login_loginButton.setOnAction(event ->{
            String loginUser = login_user.getText().trim();
            String loginPass = login_pass.getText().trim();
            User newlogin = new User();
            newlogin.setUsername(loginUser);
            newlogin.setPassword(loginPass);
            ResultSet userRow = dbhandler.getUser(newlogin);
            int counter = 0;
            try {
                while(userRow.next()) {
                    counter++;
                    String name = userRow.getString("firstname");
                    System.out.println(name);
                }
                if (counter == 1){
                    System.out.println("Success");
                }else {
                    Shaker shaker = new Shaker(login_loginButton);
                    shaker.shake();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        login_signUpButton.setOnAction(event ->{
//            take user to sign up
            login_signUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }



}
