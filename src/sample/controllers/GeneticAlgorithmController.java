package sample.controllers;

import javafx.geometry.Orientation;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Font;
import javafx.stage.Stage;


public class GeneticAlgorithmController {

    private GeneticAlgorithmView geneticAlgorithmView = new GeneticAlgorithmView();
    static final int WIDTH = 1277;
    static final int HEIGHT = 900;


    AnchorPane mainRoot;

    private void addButton(Button button, AnchorPane anchorPane) {
        button.setLayoutX(1350);
        button.setLayoutY(200);
        button.setPrefHeight(25);
        button.setPrefWidth(119);
        button.setText("Return to Menu");
        button.setMnemonicParsing(false);
        anchorPane.getChildren().add(button);
    }

    private void addSeparator(Separator separator, AnchorPane anchorPane) {
        separator.setLayoutY(0);
        separator.setOrientation(Orientation.VERTICAL);
        separator.setLayoutX(827);
        separator.setPrefHeight(10000);
        separator.setPrefWidth(900);
        anchorPane.getChildren().add(separator);
    }

    private void addLabel1(Label label, AnchorPane anchorPane) {
        label.setLayoutX(1330);
        label.setLayoutY(60);
        label.setPrefHeight(17);
        label.setFont(new Font(16));
        label.setStyle("-fx-font: Bold");
        label.setPrefWidth(400);
        anchorPane.getChildren().add(label);
    }

    private void addLabel(Label label, AnchorPane anchorPane) {
        label.setLayoutX(1330);
        label.setLayoutY(100);
        label.setPrefHeight(17);
        label.setText("Population Size: " + this.geneticAlgorithmView.population.getPopulation().size());
        label.setFont(new Font(16));
        label.setStyle("-fx-font: Bold");
        label.setPrefWidth(400);
        anchorPane.getChildren().add(label);
    }

    private void addLabel2(Label label, AnchorPane anchorPane) {
        label.setLayoutX(1330);
        label.setLayoutY(140);
        label.setPrefHeight(17);
        label.setFont(new Font(16));
        label.setStyle("-fx-font: Bold");
        label.setPrefWidth(400);
        anchorPane.getChildren().add(label);
    }

    private void addGeneticAlGeneticAlgorithmView(GeneticAlgorithmView geneticAlgorithmView, AnchorPane anchorPane){
        geneticAlgorithmView.setLayoutX(0);
        geneticAlgorithmView.setLayoutY(0);
        geneticAlgorithmView.setPrefHeight(HEIGHT);
        geneticAlgorithmView.setPrefWidth(WIDTH);
        anchorPane.getChildren().add(geneticAlgorithmView);
    }

    public void start(Stage primaryStage) throws Exception {
        mainRoot = new AnchorPane();
        Separator separator = new Separator();
        Button button = new Button();
        button.setOnAction(event -> {
            try {
                handleReturnToMenuButton(button);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Label label1 = new Label();
        Label label = new Label();
        Label label2 = new Label();
        addGeneticAlGeneticAlgorithmView(geneticAlgorithmView,mainRoot);
        addSeparator(separator, mainRoot);
        addButton(button, mainRoot);
        addLabel(label, mainRoot);
        addLabel1(label1,mainRoot);
        addLabel2(label2,mainRoot);
        Scene scene = new Scene(mainRoot, WIDTH, HEIGHT);
        primaryStage.setTitle("Genetic Algorithm");
        primaryStage.setScene(scene);
        primaryStage.show();
        geneticAlgorithmView.draw(label2);
        geneticAlgorithmView.totalCities(label1);
        primaryStage.setFullScreen(true);
    }


    private void handleReturnToMenuButton(Button button) throws Exception {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        Main mainController = new Main();
        mainController.start(new Stage());
    }

}
