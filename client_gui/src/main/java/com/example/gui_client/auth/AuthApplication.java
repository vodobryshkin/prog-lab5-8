package com.example.gui_client.auth;

import com.example.gui_client.GuiApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;

public class AuthApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Используем глобальную переменную для языка
        ResourceBundle bundle = ResourceBundle.getBundle(
                "com.example.gui_client.messages",
                GuiApp.getCurrentLocale()
        );

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