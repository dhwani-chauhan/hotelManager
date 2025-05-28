package main.roombooking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.checkin.CheckInController;
import main.homepage.HomePageController;
import main.hotel.Guest;
import main.hotel.Reservation;
import main.hotel.Room;
import main.login.LoginController;

import java.net.URL;
import java.util.ResourceBundle;

import static main.checkin.CheckInController.getDifferenceDays;
import static main.checkin.CheckInController.reservation;

public class RoomBookingController implements Initializable {

    @FXML
    public Label Username, backLabel;
    @FXML
    public Hyperlink logoutLink;
    @FXML
    public JFXButton logoBtn, submitBtn, clearBtn, searchRoomBtn, roomBtn, guestsBtn;
    @FXML
    public TextField roomId, Name, Phone, Email, City, Nationality, Passport, Address, CardNumber, CVCCode;
    @FXML
    public RadioButton Economy, Normal, Vip, Single, Double, Triple;
    @FXML
    public JFXDatePicker checkInDate, checkOutDate;
    @FXML
    public ToggleGroup roomType, roomCapacity;

    CheckInController c = new CheckInController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Username.setText(LoginController.user.getUserName());
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

    public String getRoomTypeValue(){
        String RoomType = null;
        if (Economy.isSelected())
            RoomType = "Economy";
        else if (Normal.isSelected())
            RoomType = "Normal";
        else if(Vip.isSelected())
            RoomType = "Vip";
        return RoomType;
    }

    public String getRoomCapacityValue(){
        String RoomCapacity = null;
        if (Single.isSelected())
            RoomCapacity = "Single";
        else if (Double.isSelected())
            RoomCapacity = "Double";
        else if (Triple.isSelected())
            RoomCapacity = "Triple";
        return RoomCapacity;
    }
    public boolean isFieldsEmpty(){
        try {

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        if ("".equals(Name.getText()) || "".equals(Phone.getText()) ||
                "".equals(Email.getText()) || "".equals(Address.getText()) ||
                "".equals(City.getText()) || "".equals(Nationality.getText()) ||
                "".equals(Passport.getText()) || "".equals(CardNumber.getText()) ||
                "".equals(CVCCode.getText()) || "".equals(roomId.getText())){
            System.out.println("Inside if of isFieldEmpty!");
            return true;
        }else {
            System.out.println("Inside else of isFieldEmpty!");
            return false;
        }
    }

    @FXML
    public void submitAction(Event event){
        try{
            if(!isFieldsEmpty()){
                if (getDifferenceDays(c.LocalDateToDate(checkInDate.getValue()),c.LocalDateToDate(checkOutDate.getValue())) < 0){
                    System.out.println("Date Diff -ve!");
                    return;
                }
                Room room = new Room(getRoomTypeValue(),getRoomCapacityValue(),c.LocalDateToDate(checkInDate.getValue()),c.LocalDateToDate(checkOutDate.getValue()),false);
                Guest guest = new Guest(Integer.parseInt(roomId.getText()),getDifferenceDays(c.LocalDateToDate(checkInDate.getValue()),c.LocalDateToDate(checkOutDate.getValue())),
                        Name.getText(),Email.getText(),Address.getText(),City.getText(),Nationality.getText(),Passport.getText(),Phone.getText(),CardNumber.getText(),CVCCode.getText(),0);
                guest.setFees(guest.CustomerRoomFees(room));
                reservation = new Reservation(room,guest);
                new main.project.switchScreen().popUp(event,"/roombooking/ConfirmData.fxml",1014,1010,"Confirm Data","/img/icon.jpg");
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.OK);
                alert.setContentText("Fill all the fields to go to Confirmation Window!");
                alert.setHeaderText("Fill all Fields");
                alert.setTitle("Error");
                ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all the fields!");
            alert.setHeaderText("Fill all Fields");
            alert.setTitle("Error");
            alert.showAndWait();
        }
    }

    @FXML
    public void searchRooms(Event event){
        try {
            if (!isFieldsEmpty()) {
                if (getDifferenceDays(c.LocalDateToDate(checkInDate.getValue()), c.LocalDateToDate(checkOutDate.getValue())) < 0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
                    alert.setContentText("Not valid value for check-in OR check-out Date \n Try again!");
                    alert.setHeaderText("Select Correct Date");
                    alert.setTitle("Error");
                    ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
                    alert.showAndWait();
                }
            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all the fields!");
            alert.setHeaderText("Fill all Fields");
            alert.setTitle("Error");
            alert.showAndWait();
        }
            Room room = Room.Search_available_rooms(getRoomTypeValue(),getRoomCapacityValue());
            if (room == null)
                roomId.setText("No Match!");
            else
                roomId.setText(room.getRoomID() + "");

    }
}
