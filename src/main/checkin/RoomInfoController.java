package main.checkin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import main.homepage.HomePageController;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomInfoController implements Initializable {

    @FXML
    public Label usernameLabel, backLabel;
    @FXML
    public Hyperlink logoutLink;
    @FXML
    public JFXButton logoBtn, submitBtn, clearBtn, searchRoomBtn, roomBtn, guestsBtn;
    @FXML
    public JFXTextField roomId;
    @FXML
    public RadioButton Economy, Normal, Vip, Single, Double, Triple;
    @FXML
    public JFXDatePicker checkInDate, checkOutDate;
    @FXML
    public ToggleGroup roomType, roomCapacity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    private void goToCheckOut(Event event){
        new HomePageController().goTo_check_out(event);
    }

    @FXML
    private void goToCancelBooking(Event event){
        new HomePageController().goTo_cancel_booking(event);
    }

    @FXML
    private void goToRooms(Event event){
        new HomePageController().goTo_rooms(event);
    }

    @FXML
    private void goToGuests(Event event){
        new HomePageController().goTo_guests(event);
    }

    @FXML
    private void goToHomePage(Event event){
        new CheckInController().goToHomePage(event);
    }

    @FXML
    private void logOut(Event event){
        new HomePageController().logOut(event);
    }

    @FXML
    private void submitAction(Event event){
        new CheckInController().submitAction(event);
    }
    @FXML
    private void clearButtonAction(ActionEvent event){
        new HomePageController().goTo_check_in(event);
    }

    @FXML
    private void searchRooms(Event event){
        new CheckInController().searchRooms(event);
    }
}
