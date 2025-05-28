package main.project;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.project.Paths;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LauncherSceneController implements Initializable {

    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private ImageView imageView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PauseTransition delay = new PauseTransition(Duration.seconds(2.18));
        delay.setOnFinished((ActionEvent event) ->{
            System.out.println("Begin");
            goToLogin(event);
            System.out.println("Done!");
        });
        delay.play();
    }
    private void goToLogin(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(Paths.LOGINVIEW));
            Stage window = new Stage();
            JFXDecorator decorator = new JFXDecorator(window,root,false,false,true);
            String uri = getClass().getResource("decoratorStyle.css").toExternalForm();
            Scene scene = new Scene(decorator);
            scene.getStylesheets().add(uri);
            window.setScene(scene);
            Image icon = new Image(getClass().getResourceAsStream("/img/login_icon.png"));
            window.getIcons().add(icon);
            window.show();
            root.requestFocus();
            ((Stage)imageView.getScene().getWindow()).close();
        }catch (IOException e){
            System.out.println("Error load LoginFXML!");
        }
    }
}
