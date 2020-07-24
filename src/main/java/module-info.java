module testTask {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens org.sample to javafx.fxml;
    exports org.sample;
}