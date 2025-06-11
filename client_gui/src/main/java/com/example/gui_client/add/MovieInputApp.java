package com.example.gui_client.add;

import com.example.gui_client.GuiApp;
import entities.classes.Movie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class MovieInputApp extends Application {

    private MovieInputController controller;
    private Stage primaryStage;
    private Locale currentLocale = new Locale("ru");

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        loadMainScene();
    }

    private void loadMainScene() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("com.example.gui_client.messages", GuiApp.getCurrentLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gui_client/add.fxml"), bundle);
        Parent root = loader.load();

        controller = loader.getController();

        // Добавляем кнопки управления
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().add(createControlPanel());
        mainLayout.getChildren().add(root);

        Scene scene = new Scene(mainLayout, 500, 800);
        primaryStage.setTitle(bundle.getString("add_movie_window_title"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createControlPanel() {
        ResourceBundle bundle = ResourceBundle.getBundle("com.example.gui_client.messages", GuiApp.getCurrentLocale());
        VBox controlPanel = new VBox(10);
        controlPanel.setStyle("-fx-padding: 10;");


        // Кнопка создания фильма
        Button createButton = new Button(bundle.getString("create_movie"));
        createButton.setOnAction(e -> createMovie());

        controlPanel.getChildren().addAll(createButton);
        return controlPanel;
    }

    private void createMovie() {
        try {
            Movie movie = controller.createMovie();
        } catch (IllegalArgumentException e) {
            showErrorAlert("Input Error", e.getMessage());
        } catch (Exception e) {
            showErrorAlert("Unexpected Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    private void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}