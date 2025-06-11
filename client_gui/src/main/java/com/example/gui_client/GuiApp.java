package com.example.gui_client;

import com.example.gui_client.auth.AuthApplication;
import com.example.gui_client.network.UDPClient;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class GuiApp extends Application {
    public static UDPClient udpClient;
    public static boolean authorized = false;
    public static String username = "vavan";

    // Глобальная переменная для отслеживания текущего языка
    public static Locale currentLocale = new Locale("ru"); // По умолчанию русский

    @Override
    public void start(Stage stage) throws IOException {
        udpClient = new UDPClient();
        AuthApplication app = new AuthApplication();
        app.start(new Stage());
    }

    public static void main(String[] args) {
        launch();
    }

    public static void setCurrentLocale(Locale locale) {
        currentLocale = locale;
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }
}