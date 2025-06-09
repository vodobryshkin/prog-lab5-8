package com.example.gui_client.auth;

import com.example.gui_client.GuiApp;
import domain.chat.classes.CommandBuffer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthController {
    @FXML private Tab authPane;
    @FXML private Tab registPane;
    @FXML private TextField authInputLoginTextField;
    @FXML private PasswordField authInputPasswordTextField;
    @FXML private Button authButton;
    @FXML private Hyperlink authHyperlink;
    @FXML private Text authErrorMessage;
    @FXML private TextField registInputLoginTextField;
    @FXML private PasswordField registInputPasswordTextField;
    @FXML private Button registButton;
    @FXML private Hyperlink registHyperlink;
    @FXML private Text registErrorMessage;
    @FXML private MenuBar authRegMenuBar;
    @FXML private Menu languageMenu;
    @FXML private MenuItem russianMenuItem;
    @FXML private MenuItem icelandMenuItem;
    @FXML private MenuItem lithuanianMenuItem;
    @FXML private MenuItem mexicanMenuItem;

    // Исправленные текстовые метки
    @FXML private Text authInputLoginText;
    @FXML private Text authInputPasswordText;
    @FXML private Text registInputLoginText;
    @FXML private Text registInputPasswordText;

    private ResourceBundle bundle;
    private Locale currentLocale;
    private Stage primaryStage;

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    @FXML
    private void initialize() {
        changeLanguage(new Locale("ru"));

        authButton.setOnAction(event -> {
            try {
                handleAuth();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        registButton.setOnAction(event -> {
            try {
                handleRegistration();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        authHyperlink.setOnAction(event -> switchToRegistration());
        registHyperlink.setOnAction(event -> switchToAuth());

        russianMenuItem.setOnAction(e -> changeLanguage(new Locale("ru")));
        icelandMenuItem.setOnAction(e -> changeLanguage(new Locale("is")));
        lithuanianMenuItem.setOnAction(e -> changeLanguage(new Locale("lt")));
        mexicanMenuItem.setOnAction(e -> changeLanguage("es", "MX"));
    }

    private void changeLanguage(Locale locale) {
        currentLocale = locale;
        bundle = ResourceBundle.getBundle("com.example.gui_client.messages", locale);
        updateUI();
    }

    private void changeLanguage(String language, String country) {
        changeLanguage(new Locale(language, country));
    }

    private void updateUI() {
        authPane.setText(bundle.getString("auth_title"));
        registPane.setText(bundle.getString("reg_title"));

        authInputLoginText.setText(bundle.getString("username"));
        authInputPasswordText.setText(bundle.getString("password"));
        registInputLoginText.setText(bundle.getString("username"));
        registInputPasswordText.setText(bundle.getString("password"));

        authButton.setText(bundle.getString("login"));
        registButton.setText(bundle.getString("create_user"));

        authHyperlink.setText(bundle.getString("no_account"));
        registHyperlink.setText(bundle.getString("have_account"));

        languageMenu.setText(bundle.getString("language"));
        russianMenuItem.setText(bundle.getString("russian"));
        icelandMenuItem.setText(bundle.getString("icelandic"));
        lithuanianMenuItem.setText(bundle.getString("lithuanian"));
        mexicanMenuItem.setText(bundle.getString("mexican"));

        if (primaryStage != null) {
            primaryStage.setTitle(bundle.getString("window_title"));
        }
    }

    private void handleAuth() throws IOException, ClassNotFoundException {
        CommandBuffer message = new CommandBuffer("auth");
        String login = authInputLoginTextField.getText();
        String password = authInputPasswordTextField.getText();
        message.setLogin(login);
        message.setPassword(password);
        GuiApp.udpClient.sendCommand(message);
    }

    private void handleRegistration() throws IOException, ClassNotFoundException {
        CommandBuffer message = new CommandBuffer("register");
        String login = registInputLoginTextField.getText();
        String password = registInputPasswordTextField.getText();
        message.setLogin(login);
        message.setPassword(password);
        GuiApp.udpClient.sendCommand(message);
    }

    private void switchToRegistration() {
        registPane.getTabPane().getSelectionModel().select(registPane);
    }

    private void switchToAuth() {
        authPane.getTabPane().getSelectionModel().select(authPane);
    }
}