package main.adduser;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.project.DataBase;
import main.project.Paths;
import main.project.User;
import java.net.URL;
import java.util.ResourceBundle;

import static main.homepage.HomePageController.decorator1;

public class AddUserController implements Initializable {
    @FXML
    private JFXTextField userNameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXButton buttonLogIn;
    @FXML
    private ImageView key_pic_Login_Btn;
    @FXML
    private JFXToggleButton isAdminButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void NewUserAction(Event event){
        if ("".equals(userNameField.getText()) || "".equals(passwordField.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING,"", ButtonType.OK);
            alert.setHeaderText("Fill all Fields..");
            alert.setTitle("Error");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
            alert.showAndWait();
            return;
        }
        try {
            System.out.println("1");
            boolean AdminStatus = isAdminButton.isSelected();
            System.out.println("2");
            User user = new User(userNameField.getText(),passwordField.getText(),AdminStatus);
            System.out.println("3");
            boolean saveUser = DataBase.SaveUser(user);
            System.out.println("4");
            if(!saveUser){
                System.out.println("5");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.OK);
                alert.setHeaderText("User Name is taken. Try again..");
                alert.setTitle("Error");
                System.out.println("6");
                ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
                System.out.println("7");
                alert.showAndWait();
                System.out.println("8");
            }
            System.out.println("9");
            passwordField.setText("");
            userNameField.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"",ButtonType.OK);
            alert.setHeaderText("User Added Successfully..");
            alert.setTitle("Notification");
            alert.showAndWait();
            System.out.println("10");
        } catch (Exception e) {
            System.out.println("1111111");
            System.out.println("e");
            System.out.println("e");
            System.out.println("e");
            e.printStackTrace();
            System.out.println("77777777777777");
        }
    }

    @FXML
    private void isAdminButtonAction(ActionEvent event){
        if (isAdminButton.isSelected())
            isAdminButton.setText("Admin");
        else
            isAdminButton.setText("Receptionist");
    }

    @FXML
    public void goToUsers(Event event){
        System.out.println("Users label Clicked");
        try{
            Parent root = FXMLLoader.load(getClass().getResource(Paths.USERSVIEW));
            decorator1.setContent(root);
            decorator1.setTitle("Users");
            root.requestFocus();
        } catch (Exception e) {
            System.out.println("Error load users FXML!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
