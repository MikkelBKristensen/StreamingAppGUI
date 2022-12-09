module com.example.streamingappgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.streamingappgui to javafx.fxml;
    exports com.example.streamingappgui;
}