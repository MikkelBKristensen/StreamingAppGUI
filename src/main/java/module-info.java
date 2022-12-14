module StreamingAppGUI {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;


    opens presentation to javafx.fxml;
    exports presentation;
}