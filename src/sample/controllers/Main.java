package sample.controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        URL url = new File("src/sample/resources/MainScene.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Travelling Salesman");
        primaryStage.setScene(new Scene(root, 788, 485));
        primaryStage.show();
    }
    

}


