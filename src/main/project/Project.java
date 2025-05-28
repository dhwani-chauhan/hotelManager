package main.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Project extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("launcherScene.fxml"));
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            Image icon = new Image(getClass().getResourceAsStream("/img/launcher_icon.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Launcher Scene Error");
        }
    }

    public static void main(String[] args){
        launch(args);
        //Project.main(args);
    }
}
