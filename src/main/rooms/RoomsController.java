package main.rooms;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.checkin.CheckInController;
import main.homepage.HomePageController;
import main.hotel.Room;
import main.login.LoginController;
import main.project.DataBase;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomsController implements Initializable {

    @FXML
    private Label backLabel, usernameLabel;
    @FXML
    private JFXButton LogoBtn;
    @FXML
    private Hyperlink logoutLink;
    @FXML
    private TableView<Room> table2;
    @FXML
    private TableColumn<Room, String> c1, c2, c3, c4, c5, c6;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Room> rooms = DataBase.getRooms();
        for (int i = 0; i < rooms.size(); i++) {
            System.out.println("aa" + rooms.get(i));
        }
        c1.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        c2.setCellValueFactory(new PropertyValueFactory<>("room_Type"));
        c3.setCellValueFactory(new PropertyValueFactory<>("room_capacity"));
        c4.setCellValueFactory(new PropertyValueFactory<>("Check_In_Date"));
        c5.setCellValueFactory(new PropertyValueFactory<>("Check_Out_Date"));
        c6.setCellValueFactory(new PropertyValueFactory<>("isEmpty"));
        try {
            table2.getItems().addAll(rooms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        usernameLabel.setText(LoginController.user.getUserName());
    }

    @FXML
    private void logOut(Event event){
        new HomePageController().logOut(event);
    }

    @FXML
    private void goToHomePage(Event event){
        new CheckInController().goToHomePage(event);
    }

    @FXML
    private void goToCheckIn(ActionEvent event){
        new HomePageController().goTo_check_in(event);
    }

    @FXML
    private void goToRoomBooking(ActionEvent event){
        new HomePageController().goTo_room_booking(event);
    }

    @FXML
    private void goToCancelBooking(ActionEvent event){
        new HomePageController().goTo_cancel_booking(event);
    }

    @FXML
    private void goToCheckOut(Event event){
        new HomePageController().goTo_check_out(event);
    }

    @FXML
    private void goToRooms(ActionEvent event){
        new HomePageController().goTo_rooms(event);
    }

    @FXML
    private void goToGuests(ActionEvent event){
        new HomePageController().goTo_guests(event);
    }

}
