package sample.controller;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.animations.Shaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class additemController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane additemTopRoot;

    @FXML
    private ImageView addButton;

    @FXML
    private Label notaskLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{

            Shaker buttonshaker = new Shaker(addButton);
            buttonshaker.shake();
            FadeTransition buttonfade = new FadeTransition(Duration.millis(2000), addButton);
            FadeTransition labelfade = new FadeTransition(Duration.millis(2000), notaskLabel);

            System.out.println("Added Clicked");
            addButton.relocate(0,20);
            notaskLabel.relocate(0,85);
            addButton.setOpacity(0);
            notaskLabel.setOpacity(0);

            buttonfade.setFromValue(1f);
            buttonfade.setToValue(0f);
            buttonfade.setCycleCount(1);
            buttonfade.setAutoReverse(false);
            buttonfade.play();

            labelfade.setFromValue(1f);
            labelfade.setToValue(0f);
            labelfade.setCycleCount(1);
            labelfade.setAutoReverse(false);
            labelfade.play();

            try {
                AnchorPane formpane = FXMLLoader.load(getClass().getResource("/sample/view/additemform.fxml"));
                FadeTransition rootTransition = new FadeTransition(Duration.millis(2000),formpane);
                rootTransition.setFromValue(0f);
                rootTransition.setToValue(1f);
                rootTransition.setCycleCount(1);
                rootTransition.setAutoReverse(false);
                rootTransition.play();
                additemTopRoot.getChildren().setAll(formpane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
