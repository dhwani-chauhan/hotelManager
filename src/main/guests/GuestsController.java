package main.guests;

import com.jfoenix.controls.JFXButton;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.hotel.Guest;
import main.homepage.HomePageController;
import main.checkin.CheckInController;

import java.net.URL;
import java.util.ResourceBundle;

public class GuestsController implements Initializable {

    @FXML
    private Label backLabel, usernameLabel;
    @FXML
    private JFXButton logoBtn;
    @FXML
    private Hyperlink logoutLink;
    @FXML
    private TableView<Guest> table2;
    @FXML
    private TableColumn<Guest, String> c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    private void goToCheckIn(Event event){
        new HomePageController().goTo_check_in(event);
    }

    @FXML
    private void goToRoomBooking(Event event){
        new HomePageController().goTo_room_booking(event);
    }

    @FXML
    private void goToCancelBooking(Event event){
        new HomePageController().goTo_cancel_booking(event);
    }

    @FXML
    private void goToCheckOut(Event event){
        new HomePageController().goTo_check_out(event);
    }

    @FXML
    private void goToRooms(Event event){
        new HomePageController().goTo_rooms(event);
    }

    @FXML
    private void goToGuests(Event event){
        new HomePageController().goTo_guests(event);
    }
}
