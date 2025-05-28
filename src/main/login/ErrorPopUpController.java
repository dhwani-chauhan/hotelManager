package main.login;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorPopUpController implements Initializable {
    @FXML
    private Label messageLabel, messageLabel1;
    @FXML
    private JFXButton okbtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void closePopUpAction(ActionEvent event){
        okbtn.getScene().getWindow().hide();
    }
}
