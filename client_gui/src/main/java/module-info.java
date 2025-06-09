module com.example.gui_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires common;

    opens com.example.gui_client to javafx.fxml;
    opens com.example.gui_client.auth to javafx.fxml;
    exports com.example.gui_client;
    exports com.example.gui_client.auth;
}