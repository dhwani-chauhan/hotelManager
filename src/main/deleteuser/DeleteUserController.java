package main.deleteuser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.login.LoginController;
import main.project.DataBase;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteUserController implements Initializable {

    @FXML
    private JFXTextField UserName;
    @FXML
    private JFXButton LoginBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void DeleteAction(ActionEvent event){
        if("".equals(UserName.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING,"", ButtonType.OK);
            alert.setHeaderText("Fill all fields");
            alert.setTitle("Error");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/WARNING_PNG.png"));
            alert.showAndWait();
        }
        if(UserName.getText().equals(LoginController.user.getUserName())){
            Alert alert = new Alert(Alert.AlertType.WARNING,"", ButtonType.OK);
            alert.setHeaderText("You can not Delete yourself!");
            alert.setTitle("Error");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/WARNING_PNG.png"));
            alert.showAndWait();
        }
        boolean deleteUser = DataBase.DeleteUser(UserName.getText());
        if(deleteUser){
            UserName.setText("");
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION,null,ButtonType.OK);
            alert1.setHeaderText("User Deleted Successfully!");
            alert1.setTitle("Notification");
            alert1.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,null,ButtonType.OK);
            alert.setHeaderText("User Name not valid, Try again!");
            alert.setTitle("Error");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
            alert.showAndWait();
        }
    }

    @FXML
    private void goToUsers(Event event){
        new main.adduser.AddUserController().goToUsers(event);
    }
}
