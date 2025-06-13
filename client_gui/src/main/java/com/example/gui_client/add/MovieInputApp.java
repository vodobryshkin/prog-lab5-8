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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.ResourceBundle;

public class MovieInputApp extends Application {

    private MovieInputController controller;
    private Stage primaryStage;
    private Movie createdMovie = null;
    private boolean movieCreated = false;

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

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().add(createControlPanel());
        mainLayout.getChildren().add(root);

        Scene scene = new Scene(mainLayout, 500, 800);
        primaryStage.setTitle(bundle.getString("add_movie_window_title"));
        primaryStage.setScene(scene);

        primaryStage.initModality(Modality.APPLICATION_MODAL);

        primaryStage.setOnCloseRequest(e -> {
            if (!movieCreated) {
                e.consume(); // Отменяем закрытие
                showWarningAlert("Внимание", "Необходимо создать фильм перед закрытием окна");
            }
        });
    }

    private VBox createControlPanel() {
        ResourceBundle bundle = ResourceBundle.getBundle("com.example.gui_client.messages", GuiApp.getCurrentLocale());
        VBox controlPanel = new VBox(10);
        controlPanel.setStyle("-fx-padding: 10;");

        // Кнопка создания фильма
        Button createButton = new Button(bundle.getString("create_movie"));
        createButton.setOnAction(e -> createMovie());

        // Кнопка отмены
        Button cancelButton = new Button("Отмена");
        cancelButton.setOnAction(e -> {
            movieCreated = true; // Позволяем закрыть окно
            createdMovie = null; // Устанавливаем null как результат
            primaryStage.close();
        });

        controlPanel.getChildren().addAll(createButton, cancelButton);
        return controlPanel;
    }

    private void createMovie() {
        try {
            Movie movie = controller.createMovie();
            createdMovie = movie;
            movieCreated = true;
            showSuccessAlert("Успех", "Фильм успешно создан!");
            primaryStage.close();
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка ввода", e.getMessage());
        } catch (Exception e) {
            showErrorAlert("Неожиданная ошибка", "Произошла неожиданная ошибка: " + e.getMessage());
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

    public static Optional<Movie> showMovieEditDialog(Movie movieToEdit) {
        try {
            MovieInputApp app = new MovieInputApp();
            Stage stage = new Stage();
            app.start(stage);

            // Передаем данные фильма в контроллер перед показом окна
            app.controller.populateFields(movieToEdit);

            stage.showAndWait();
            return Optional.ofNullable(app.createdMovie);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private void showWarningAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static Optional<Movie> showMovieInputDialog() {
        try {
            MovieInputApp app = new MovieInputApp();
            Stage stage = new Stage();
            app.start(stage);
            stage.showAndWait(); // Теперь это безопасно, так как show() не вызывался ранее

            return Optional.ofNullable(app.createdMovie);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}