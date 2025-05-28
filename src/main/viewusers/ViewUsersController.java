package main.viewusers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.project.DataBase;
import main.project.User;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewUsersController implements Initializable {

    @FXML
    private TableColumn<User, String> c1, c2, c3;
    @FXML
    private TableView<User> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c1.setCellValueFactory(new PropertyValueFactory<>("username"));
        c2.setCellValueFactory(new PropertyValueFactory<>("password"));
        c3.setCellValueFactory(new PropertyValueFactory<>("is_admin"));
        List<User> users = DataBase.getUsers();
        table.getItems().addAll(users);
    }

    @FXML
    private void goToUsers(Event event){
        new main.adduser.AddUserController().goToUsers(event);
    }
}
