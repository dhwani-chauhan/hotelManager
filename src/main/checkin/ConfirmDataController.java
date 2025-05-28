package main.checkin;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.homepage.HomePageController;
import main.hotel.Reservation;
import main.hotel.Room;
import main.project.DataBase;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmDataController implements Initializable {

    @FXML
    private TextField Name, Phone, Email, City, Nationality, Passport, Address, CardNumber, CVCCode, roomId;
    @FXML
    private JFXButton Save, Cancel;
    @FXML
    private Label roomType, roomCapacity, noOfNights, nightCost, Empty, CheckIn, CheckOut, TotalFees;
    @FXML
    private ImageView logoImg;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Reservation reservation = CheckInController.reservation;
        Name.setText(reservation.getGuest().getName());
        Phone.setText(reservation.getGuest().getPhoneNo());
        Email.setText(reservation.getGuest().getEmail());
        Address.setText(reservation.getGuest().getAddress());
        City.setText(reservation.getGuest().getCity());
        Nationality.setText(reservation.getGuest().getNationality());
        Passport.setText(reservation.getGuest().getPassportNumber());
        CardNumber.setText(reservation.getGuest().getCardNumber());
        CVCCode.setText(reservation.getGuest().getCardPass());
        roomType.setText(reservation.getRoom().getRoom_Type());
        roomCapacity.setText(reservation.getRoom().getRoom_capacity());
        roomId.setText(reservation.getGuest().getRoomId() +"");
        CheckIn.setText(CheckInController.DateToLocalDate(reservation.getRoom().getCheck_In_Date()) + "");
        CheckOut.setText(CheckInController.DateToLocalDate(reservation.getRoom().getCheck_Out_Date()) + "");
        noOfNights.setText(reservation.getGuest().getNumberOFDaysStayed() + "");
        nightCost.setText(reservation.getRoom().nightCost(reservation.getRoom()) + "");
        TotalFees.setText(reservation.getGuest().getFees() + "$");
    }

    @FXML
    private void SaveButtonAction(ActionEvent event){
        DataBase db = new DataBase();
        Reservation reservation = CheckInController.reservation;
        Room.CheckIn(reservation.getGuest(),reservation.getRoom(),reservation.getGuest().getRoomId());
        new HomePageController().goTo_check_in(event);
        ((Stage)Name.getScene().getWindow()).close();
    }

    @FXML
    private void CancelAction(ActionEvent event){
        ((Stage)Name.getScene().getWindow()).close();
    }
}
