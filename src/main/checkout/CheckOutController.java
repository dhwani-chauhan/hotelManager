package main.checkout;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.checkin.CheckInController;
import main.homepage.HomePageController;
import main.hotel.Room;
import main.login.LoginController;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckOutController implements Initializable {

    @FXML
    private JFXButton LogoBtn, checkOut, Clear, CheckIn, roomBooking, cancelBooking, roomBtn, guestsBtn;
    @FXML
    private Label usernameLabel;
    @FXML
    private Hyperlink logoutLink;
    @FXML
    private TextField roomNo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    @FXML
    private void clearAction(ActionEvent event){
        roomNo.setText("");
    }

    @FXML
    private void checkOutAction(ActionEvent event){
        if("".equals(roomNo.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"", ButtonType.OK);
            alert.setContentText("You must fill all fields to go to confirmation window!");
            alert.setHeaderText("Fill all fields");
            alert.setTitle("Error");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
            alert.show();
        }else {
            int flag = -1;
            try{
                flag = Room.CheckOut(Integer.parseInt(roomNo.getText()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.setHeaderText("Error, room no not valid");
                alert.setTitle("Error");
                alert.show();
            }
            switch (flag){
                case 0:
                    Alert alert = new Alert(Alert.AlertType.WARNING,null,ButtonType.OK);
                    alert.setHeaderText("Room was already Empty!");
                    alert.setTitle("info");
                    alert.show();
                    break;
                case 1:
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION,null,ButtonType.OK);
                    alert1.setHeaderText("Check Out is Done!");
                    alert1.setTitle("info");
                    alert1.show();
                    new HomePageController().goTo_check_out(event);
                    break;
                default:
                    Alert alert2 = new Alert(Alert.AlertType.ERROR,null,ButtonType.OK);
                    alert2.setContentText("Room " + roomNo.getText() + " is not valid..");
                    alert2.setHeaderText("Error, room no not valid");
                    alert2.setTitle("Error");
                    alert2.show();
                    break;
            }
        }
    }

}
