package main.checkin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.hotel.Guest;
import main.hotel.Reservation;
import main.hotel.Room;
import main.login.LoginController;
import main.homepage.HomePageController;
import main.project.Paths;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static main.login.LoginController.decorator;
import static main.login.LoginController.window;

public class CheckInController implements Initializable {

    @FXML
    public Label usernameLabel, backLabel, nameError, phoneError, emailError, addressError, cityError, nationalityError, passportError;
    @FXML
    public Hyperlink logoutLink;
    @FXML
    public JFXButton logoBtn, submitBtn, clearBtn, searchRoomBtn, roomBtn, guestsBtn;
    @FXML
    public JFXTextField Name, Phone, Email, City, Nationality, Passport, Address, CardNumber, CVCcode, roomId;
    @FXML
    public RadioButton Economy, Normal, Vip, Single, Double, Triple;
    @FXML
    public JFXDatePicker checkInDate, checkOutDate;
    @FXML
    public ToggleGroup roomType, roomCapacity;

    public static Reservation reservation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(LoginController.user.getUserName());
        checkInDate.setValue(LocalDate.now());
        NumberValidator numberValidator = new NumberValidator("This Field must contain numbers only.");
        CVCcode.getValidators().add(numberValidator);
        CardNumber.getValidators().add(numberValidator);
        CVCcode.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!newValue)
                    CVCcode.validate();
            }
        });
        CardNumber.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!newValue)
                    CardNumber.validate();
            }
        });
    }
    static boolean HandleNameField = false;
    @FXML
    private void HandleNameField(Event event){
        Name.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    if (Name.getText().matches("[A-Za-z\\s]{2,}")){
                        nameError.setText("Valid");
                        nameError.setTextFill(Color.GREEN);
                    }else {
                        nameError.setText("Name must contain letters only.");
                        nameError.setTextFill(Color.RED);
                        HandleNameField = true;
                    }
                }
            }
        });
        if (HandleNameField){
            if (Name.getText().matches("[A-Za-z\\s]{2,}")){
                nameError.setText("Valid");
                nameError.setTextFill(Color.GREEN);
            }else {
                nameError.setText("Name must contain letters only.");
                nameError.setTextFill(Color.RED);
                HandleNameField = true;
            }
        }
    }

    static boolean HandlePhoneField = false;
    @FXML
    private void HandlePhoneField(Event event){
        Phone.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    if (Phone.getText().matches("[\\d\\s]+")){
                        phoneError.setText("Valid");
                        phoneError.setTextFill(Color.GREEN);
                    }else {
                        phoneError.setText("Phone number must contain digits only.");
                        phoneError.setTextFill(Color.RED);
                        HandlePhoneField = true;
                    }
                }
            }
        });
        if (HandlePhoneField){
            if (Phone.getText().matches("[\\d\\s]+")){
                phoneError.setText("Valid");
                phoneError.setTextFill(Color.GREEN);
            }else {
                phoneError.setText("Phone number must contain digits only.");
                phoneError.setTextFill(Color.RED);
            }
        }
    }

    static boolean HandleEmailField = false;
    @FXML
    private void HandleEmailField(Event event){
        Email.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    if (Email.getText().matches(EMAIL_REGEX)){
                        emailError.setText("Valid");
                        emailError.setTextFill(Color.GREEN);
                    }else {
                        emailError.setText("Must be in this form: user@domain.com");
                        emailError.setTextFill(Color.RED);
                        HandleEmailField = true;
                    }
                }
            }
        });
        if (HandleEmailField){
            String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            if (Email.getText().matches(EMAIL_REGEX)){
                emailError.setText("Valid");
                emailError.setTextFill(Color.GREEN);
            }else {
                emailError.setText("Must be in this form: user@domain.com");
                emailError.setTextFill(Color.RED);
                HandleEmailField = true;
            }
        }
    }

    @FXML
    private void logOut(Event event){
        new HomePageController().logOut(event);
    }

    @FXML
    public void goToHomePage(Event event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(Paths.HOMEPAGEVIEW));
            decorator.setContent(root);
            decorator.setCustomMaximize(true);
            decorator.setBorder(Border.EMPTY);
            decorator.setTitle("Hotel Reservation System");
            Image icon = new Image(getClass().getResourceAsStream("/img/icon.jpg"));
            window.getIcons().add(icon);
            window.setTitle("Hotel System");
            root.requestFocus();
        }catch (IOException e){
            System.out.println("Error load in Login!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToConfirmData(Event event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/checkin/ConfirmData.fxml"));
            Stage window = new Stage();
            JFXDecorator decorator1 = new JFXDecorator(window,root,false,false,true);
            Scene scene = new Scene(decorator1);
            decorator1.setTitle("Confirmation Data");
            String uri = getClass().getResource("decoratorStyle.css").toExternalForm();
            scene.getStylesheets().add(uri);
            window.setScene(scene);
            Image icon = new Image(getClass().getResourceAsStream("/img/icon.jpg"));
            window.getIcons().add(icon);
            window.setTitle("Confirmation Data");
            window.show();
            root.requestFocus();
        }catch (IOException e){
            System.out.println("Error load in Confirmation Data!");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @FXML
    public void submitAction(Event event){
        try{
            if(!isFieldsEmpty()){
                if (getDifferenceDays(LocalDateToDate(checkInDate.getValue()),LocalDateToDate(checkOutDate.getValue())) < 0){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.OK);
                    alert.setContentText("Not valid value for check-in OR check-out Date \n Try again!");
                    alert.setHeaderText("Select Correct Date");
                    alert.setTitle("Error");
                    ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
                    alert.showAndWait();
                }
                Room room = new Room(getRoomTypeValue(),getRoomCapacityValue(),LocalDateToDate(checkInDate.getValue()),LocalDateToDate(checkOutDate.getValue()),false);
                Guest guest = new Guest(Integer.parseInt(roomId.getText()),getDifferenceDays(LocalDateToDate(checkInDate.getValue()),LocalDateToDate(checkOutDate.getValue())),
                        Name.getText(),Email.getText(),Address.getText(),City.getText(),Nationality.getText(),Passport.getText(),Phone.getText(),CardNumber.getText(),CVCcode.getText(),0);
                guest.setFees(guest.CustomerRoomFees(room));
                reservation = new Reservation(room,guest);
                goToConfirmData(event);
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
                "".equals(CVCcode.getText()) || "".equals(roomId.getText())){
            System.out.println("Inside if of isFieldEmpty!");
            return true;
        }else {
            System.out.println("Inside else of isFieldEmpty!");
            return false;
        }
    }

    @FXML
    private void clearButtonAction(ActionEvent event){
        System.out.println("Clear button clicked");
        new HomePageController().goTo_check_in(event);
    }

    public boolean comp(Date a, Date b, Date d){
        return a.compareTo(d) * d.compareTo(b) >= 0;
    }

    public Date LocalDateToDate(LocalDate ld){
        Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static int getDifferenceDays(Date d1,Date d2){
        long difference = d2.getTime() - d1.getTime();
        return (int) TimeUnit.DAYS.convert(difference,TimeUnit.MILLISECONDS);
    }

    public static LocalDate DateToLocalDate(Date date){
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @FXML
    public void searchRooms(Event event){
        try {
            if (!isFieldsEmpty()) {
                if (getDifferenceDays(LocalDateToDate(checkInDate.getValue()), LocalDateToDate(checkOutDate.getValue())) < 0) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
                    alert.setContentText("Not valid value for check-in OR check-out Date \n Try again!");
                    alert.setHeaderText("Select Correct Date");
                    alert.setTitle("Error");
                    ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Fill all the fields!");
                    alert.setHeaderText("Fill all Fields");
                    alert.setTitle("Error");
                    alert.showAndWait();
                }
            } else if (getDifferenceDays(LocalDateToDate(checkInDate.getValue()), LocalDateToDate(checkOutDate.getValue())) < 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK);
                alert.setContentText("Not valid value for check-in OR check-out Date \n Try again!");
                alert.setHeaderText("Select Correct Date");
                alert.setTitle("Error");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/img/Error01.png"));
                alert.showAndWait();
            }else {
                Room room = Room.Search_available_rooms(getRoomTypeValue(),getRoomCapacityValue());
                if (room == null)
                    roomId.setText("No Match!");
                else
                    roomId.setText(room.getRoomID() + "");
            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all the fields!");
            alert.setHeaderText("Fill all Fields");
            alert.setTitle("Error");
            alert.showAndWait();
        }
    }
}
