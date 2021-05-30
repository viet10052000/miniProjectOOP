package sample.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;


public class GenerateDataController {

    @FXML
    public Button submitButton;

    @FXML
    public TextField NumberOfPoints;

    @FXML
    public Label validator;

    public int ValueFromTextField = 0;

    private TSPGene[] CITIES;
    private sample.controllers2.TSPGene[] CITIESCONTROLLER2;
    private sample.controllers3.TSPGene[] CITIESCONTROLLER3;
    private int IntegerPoints;

    public GenerateDataController() {
    }

    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/sample/resources/generateData.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Generate Data");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public boolean getValueFromTextField()
    {
        try {
            ValueFromTextField = Integer.parseInt(NumberOfPoints.getText());
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }


    void drawData() {
        if(!getValueFromTextField())
        {
            validator.setText("Invalid number !");
        }
        this.IntegerPoints = ValueFromTextField;
        TSPUtils tspUtils = new TSPUtils();
        tspUtils.setCITIES(CITIES,IntegerPoints);

        sample.controllers2.TSPUtils tspUtilsController2 = new sample.controllers2.TSPUtils();
        tspUtilsController2.setCITIES(CITIESCONTROLLER2,IntegerPoints);

        sample.controllers3.TSPUtils tspUtilsController3 = new sample.controllers3.TSPUtils();
        tspUtilsController3.setCITIES(CITIESCONTROLLER3,IntegerPoints);
    }


    @FXML
    void generateData() throws Exception {
        drawData();
        Stage stage = (Stage) NumberOfPoints.getScene().getWindow();
        if(getValueFromTextField())
        {
            stage.close();
        }
    }
}
