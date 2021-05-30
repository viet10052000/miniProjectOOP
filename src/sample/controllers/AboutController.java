package sample.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class AboutController {
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/sample/resources/about.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("About Application");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
