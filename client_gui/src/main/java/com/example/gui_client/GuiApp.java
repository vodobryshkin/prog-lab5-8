package com.example.gui_client;

import com.example.gui_client.auth.AuthApplication;
import com.example.gui_client.network.UDPClient;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiApp extends Application {
    public static UDPClient udpClient;
    @Override
    public void start(Stage stage) throws IOException {
       udpClient = new UDPClient();
        AuthApplication app = new AuthApplication();
        app.start(new Stage());
    }

    public static void main(String[] args) {
        launch();
    }
}