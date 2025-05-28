package main.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.project.DataBase;
import main.project.Paths;
import main.project.User;
import main.project.switchScreen;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public static Stage window;
    public static JFXDecorator decorator;
    public static User user;
    @FXML
    private JFXButton ButtonLogin;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXTextField UserNameField;
    @FXML
    private JFXPasswordField PasswordField;
    @FXML
    private ImageView key_pic_Login_Btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Login Initialize");
    }

    @FXML
    private void loginAction(Event event){
        try{
            DataBase.CheckConnection();
            System.out.println("Check Connection True");
        } catch (Exception e) {
            System.out.println("Error in check connection");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Connection Error");
            alert.setContentText("Error while trying to connect to Database..");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/WARNING_PNG.png"));
            alert.show();
            return;
        }
        user = new User(UserNameField.getText(),PasswordField.getText(),false);
        boolean userIsAdmin = User.isUserAdmin(user);
        user.setIs_admin(userIsAdmin);
        try {
            if(User.isUserValid(user))
                goTohomePage(event);
            else {
                try {
                    System.out.println("Executing PopUp");
                    new switchScreen().popUp(event,"/login/ErrorPopUp.fxml",370,250,"","/img/Error01.png");
                } catch (Exception e) {
                    System.out.println("Error on PopUp window");
                    System.out.println(e);
                }
            }
        }catch (Exception e) {
            System.out.println("is user valid Exception");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Connection Error");
            alert.setContentText("Error while trying to connect to Database..");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
            alert.show();
            return;
        }
    }

    public void goTohomePage(Event event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(Paths.HOMEPAGEVIEW));
            window = new Stage();
            decorator = new JFXDecorator(window,root,false,false,true);
            int width = 1400,height = 1000;
            Scene scene = new Scene(decorator,width,height);
            decorator.setTitle("Hotel Reservation System");
            String uri = getClass().getResource("decoratorStyle.css").toExternalForm();
            scene.getStylesheets().add(uri);
            window.setScene(scene);
            window.setMaxHeight(height);
            window.setMinHeight(height);
            window.setMaxWidth(width);
            window.setMinWidth(width);
            window.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.jpg")));
            window.setTitle("Hotel System");
            window.show();
            root.requestFocus();
            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        }catch (IOException e){
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
