package com.example.gui_client.auth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Locale defaultLocale = new Locale("ru");
        ResourceBundle bundle = ResourceBundle.getBundle("com.example.gui_client.messages", defaultLocale);

        FXMLLoader fxmlLoader = new FXMLLoader(
                AuthApplication.class.getResource("/com/example/gui_client/auth.fxml"),
                bundle
        );

        Scene scene = new Scene(fxmlLoader.load());

        AuthController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle(bundle.getString("window_title"));
        stage.setScene(scene);
        stage.show();
    }
}