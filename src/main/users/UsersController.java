package main.users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.project.Paths;
import static main.homepage.HomePageController.decorator1;
import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable {

    @FXML
    private ImageView key_pic_Login_Btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void NewUserAction(MouseEvent event){

    }

    @FXML
    private void AddUser(ActionEvent event){
        System.out.println("Add User Clicked");
        try{
            Parent root = FXMLLoader.load(getClass().getResource(Paths.ADDUSERVIEW));
            decorator1.setContent(root);
            decorator1.setTitle("Add User");
            root.requestFocus();
        } catch (Exception e) {
            System.out.println("Error load Add User!");
            e.printStackTrace();
        }
    }

    @FXML
    private void DeleteUser(ActionEvent event){
        System.out.println("Delete User Clicked");
        try{
            Parent root = FXMLLoader.load(getClass().getResource(Paths.DELETEUSERVIEW));
            decorator1.setContent(root);
            decorator1.setTitle("Delete User");
            root.requestFocus();
        } catch (Exception e) {
            System.out.println("Error load Delete User!");
            e.printStackTrace();
        }
    }

    @FXML
    private void ViewUsers(ActionEvent event){
        System.out.println("View all Users Clicked");
        try{
            Parent root = FXMLLoader.load(getClass().getResource(Paths.VIEWUSERSVIEW));
            decorator1.setContent(root);
            decorator1.setTitle("View Users");
            root.requestFocus();
        } catch (Exception e) {
            System.out.println("Error load View all Users!");
            e.printStackTrace();
        }
    }
}
