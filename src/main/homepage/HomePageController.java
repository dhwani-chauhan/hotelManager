package main.homepage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.hotel.Room;
import main.login.LoginController;
import main.project.DataBase;
import main.project.Paths;

import static main.login.LoginController.decorator;
import static main.login.LoginController.window;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private JFXButton check_in_button,check_out_button,room_booking_button,cancel_booking_button;
    @FXML
    private Label username1, username11, username111, rank_label;
    @FXML
    private ProgressIndicator available_par, reserved_par;
    @FXML
    private JFXButton roomBTN;
    @FXML
    private JFXButton guestsBTN;
    public static JFXDecorator decorator1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username1.setText(LoginController.user.getUserName());
        if (LoginController.user.isIs_admin())
            rank_label.setText("Admin");
        else
            rank_label.setText("Receptionist");
        List<Room> availableRooms = DataBase.getAvailableRooms();
        System.out.println("Size: " + availableRooms.size());
        double availablePercentage = availableRooms.size();
        available_par.setProgress(availablePercentage / 90);
        System.out.println(availablePercentage);
        double reservedPercentage = 90 - availableRooms.size();
        System.out.println(reservedPercentage);
        reserved_par.setProgress(reservedPercentage / 90);
    }

    @FXML
    public void logOut(Event event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.LOGINVIEW)));
            Stage window = new Stage();
            JFXDecorator decorator = new JFXDecorator(window,root,false,false,true);
            Scene scene = new Scene(decorator);
            String uri = getClass().getResource("decoratorStyle.css").toExternalForm();
            scene.getStylesheets().add(uri);
            int width = 690, height = 620;
            window.setScene(scene);
            window.setMaxWidth(width);
            window.setMinWidth(width);
            window.setMaxHeight(height);
            window.setMinHeight(height);
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/login_icon.png")));
            window.getIcons().add(icon);
            window.show();
            root.requestFocus();
            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        } catch (Exception e) {
            System.out.println("Error load in login!");
            e.printStackTrace();
        }
    }

    @FXML
    public void goTo_check_in(Event event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.CHECKINVIEW)));
            window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Check in");
            decorator.setContent(root);
            decorator.setTitle("Check in");
            root.requestFocus();
        }catch (Exception e) {
            System.out.println("Error load in login!");
            e.printStackTrace();
        }
    }

    @FXML
    public void goTo_room_booking(Event event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.ROOMBOOKVIEW)));
            window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Room Booking");
            decorator.setContent(root);
            decorator.setTitle("Room Booking");
            root.requestFocus();
        }catch (Exception e) {
            System.out.println("Error load in Room Booking!");
            e.printStackTrace();
        }
    }

    @FXML
    public void goTo_cancel_booking(Event event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.CANCELBOOKINGVIEW)));
            window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Cancel Booking");
            decorator.setContent(root);
            decorator.setTitle("Cancel Booking");
            root.requestFocus();
        }catch (Exception e) {
            System.out.println("Error load in Cancel Booking!");
            e.printStackTrace();
        }
    }

    @FXML
    public void goTo_check_out(Event event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.CHECKOUTVIEW)));
            window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Check out");
            decorator.setContent(root);
            decorator.setTitle("Check out");
            root.requestFocus();
        }catch (Exception e) {
            System.out.println("Error load in Check out!");
            e.printStackTrace();
        }
    }

    @FXML
    public void goTo_rooms(Event event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.ROOMSVIEW)));
            window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Rooms");
            decorator.setContent(root);
            decorator.setTitle("Rooms");
            root.requestFocus();
        }catch (Exception e) {
            System.out.println("Error load in Rooms!");
            main.project.switchScreen.Load_Error_alert("Rooms",e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goTo_guests(Event event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.GUESTSVIEW)));
            window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Guests");
            decorator.setContent(root);
            decorator.setTitle("Guests");
            root.requestFocus();
        }catch (Exception e) {
            System.out.println("Error load in Guests!");
            main.project.switchScreen.Load_Error_alert("Guests",e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void goTo_users(Event event){
        if ( LoginController.user.isIs_admin()){
            try{
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Paths.USERSVIEW)));
                Stage window2 = new Stage();
                decorator1 = new JFXDecorator(window2,root,false,false,true);
                Scene scene = new Scene(decorator1);
                String uri = getClass().getResource("decoratorStyle.css").toExternalForm();
                scene.getStylesheets().add(uri);
                int width = 690, height = 620;
                window2.setScene(scene);
                window2.setMaxWidth(width);
                window2.setMinWidth(width);
                window2.setMaxHeight(height);
                window2.setMinHeight(height);
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/login_icon.png")));
                window2.getIcons().add(icon);
                window2.show();
                root.requestFocus();
            } catch (Exception e) {
                System.out.println("Error load in Users");
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"", ButtonType.OK);
            alert.setHeaderText("Only Admin can Edit Users");
            alert.setTitle("Error");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/Error01.png"));
            alert.showAndWait();
        }
    }
}
