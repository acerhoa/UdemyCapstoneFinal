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

import java.io.IOException;
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
        String loginUser = login_user.getText().trim();
        String loginPass = login_pass.getText().trim();


        login_loginButton.setOnAction(event ->{
            if(!loginUser.equals("") || !loginPass.equals("")){
                Login(loginUser,loginPass);
            }else {
                System.out.println("Error: Try Again");
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

    private void Login(String user, String pass){
//        check if user exist

    }


}
