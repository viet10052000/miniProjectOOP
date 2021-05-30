module javaFXdemo2 {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;

    exports sample.controllers to javafx.graphics, javafx.fxml;
    opens sample.controllers to javafx.fxml;

    opens sample;
}