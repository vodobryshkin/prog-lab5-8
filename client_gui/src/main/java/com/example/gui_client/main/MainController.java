package com.example.gui_client.main;

import com.example.gui_client.GuiApp;
import com.example.gui_client.add.MovieInputApp;
import com.example.gui_client.auth.AuthApplication;
import domain.chat.classes.CommandBuffer;
import domain.chat.classes.ServerAnswerBuffer;
import domain.chat.enums.AnswerStatus;
import entities.classes.Coordinates;
import entities.classes.Location;
import entities.classes.Movie;
import entities.classes.Person;
import entities.enums.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // FXML элементы
    @FXML private MenuBar menuBar;
    @FXML private Menu languageMenu;
    @FXML private MenuItem russianMenuItem;
    @FXML private MenuItem icelandMenuItem;
    @FXML private MenuItem lithuanianMenuItem;
    @FXML private MenuItem mexicanMenuItem;

    @FXML private Text showUserNicknameText;
    @FXML private Button endSessionButton;

    @FXML private TabPane tabPane;
    @FXML private Tab tableTab;
    @FXML private Tab vizuslisationTab;

    @FXML private TableView<MovieData> tableView; // Убедитесь, что fx:id="tableView" в FXML
    @FXML private TableColumn<MovieData, String> idTableColumn;
    @FXML private TableColumn<MovieData, String> ownerTableColumn;
    @FXML private TableColumn<MovieData, String> filmNameTableColumn;
    @FXML private TableColumn<MovieData, String> coodinatesTableColumn;
    @FXML private TableColumn<MovieData, String> xCoordinatesTableColumn;
    @FXML private TableColumn<MovieData, String> yCoordinatesTableColumn;
    @FXML private TableColumn<MovieData, String> creationDateTableColumn;
    @FXML private TableColumn<MovieData, String> oscarCountTableColumn;
    @FXML private TableColumn<MovieData, String> genreTableColumn;
    @FXML private TableColumn<MovieData, String> mpaaRatingTableColumn;
    @FXML private TableColumn<MovieData, String> operatorTableColumn;
    @FXML private TableColumn<MovieData, String> nameOperatorTableColumn;
    @FXML private TableColumn<MovieData, String> heightOperatorTableColumn;
    @FXML private TableColumn<MovieData, String> eyeColorOperatorTableColumn;
    @FXML private TableColumn<MovieData, String> hairColorOperatorTableColumn;
    @FXML private TableColumn<MovieData, String> countryOperatorTableColumn;
    @FXML private TableColumn<MovieData, String> locationOperatorTableColumn;
    @FXML private TableColumn<MovieData, String> xLocationOperatorTableColumn;
    @FXML private TableColumn<MovieData, String> yLocationOperatorTableColumn;
    @FXML private TableColumn<MovieData, String> zLocationOperatorTableColumn;

    @FXML private Canvas vizualCanvas;

    // Добавленные поля для команд
    @FXML private MenuItem help;
    @FXML private MenuItem info;
    @FXML private MenuItem add;
    @FXML private MenuItem remove_by_id;
    @FXML private MenuItem remove_first;
    @FXML private MenuItem remove_lower;
    @FXML private MenuItem execute_script;
    @FXML private Menu commandOCHKA;

    private final ObservableList<MovieData> movieDataList = FXCollections.observableArrayList();
    private ResourceBundle currentBundle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Установка данных в таблицу должна быть первой, чтобы избежать NPE
        if (tableView != null) {
            tableView.setItems(movieDataList);
        } else {
            System.err.println("CRITICAL ERROR: tableView is null. Check fx:id in main.fxml!");
            // Дальнейшее выполнение бессмысленно, если таблицы нет
            return;
        }

        setupLocalization();
        setupTableColumns();
        setupMenuHandlers();
        setupVisualization();
        setupTableContextMenu();

        showUserNicknameText.setText(getLocalizedText("user") + ": " + GuiApp.username);

        Platform.runLater(this::loadData);
    }

    // Новый метод для настройки контекстного меню
    private void setupTableContextMenu() {
        // Создаем контекстное меню для таблицы
        ContextMenu contextMenu = new ContextMenu();
        MenuItem editItem = new MenuItem(getLocalizedText("edit"));
        editItem.setOnAction(event -> handleEditAction());
        contextMenu.getItems().add(editItem);

        // Устанавливаем контекстное меню для таблицы
        tableView.setContextMenu(contextMenu);

        // Также добавим обработчик для правого клика по строке
        tableView.setRowFactory(tv -> {
            TableRow<MovieData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY && !row.isEmpty()) {
                    contextMenu.show(tableView, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });
    }

    private void handleEditAction() {
        MovieData selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (selected.ownerProperty().get().equals(GuiApp.username)) {
                editMovie(selected);
            } else {
                showErrorMessage(getLocalizedText("edit_own_only_err"));
            }
        }
    }

    private void setupLocalization() {
        updateLocalization();
    }

    private void updateLocalization() {
        try {
            currentBundle = ResourceBundle.getBundle("com.example.gui_client.messages", GuiApp.getCurrentLocale());
        } catch (MissingResourceException e) {
            currentBundle = ResourceBundle.getBundle("com.example.gui_client.messages", new Locale("ru"));
        }
        updateUITexts();
    }

    private void updateUITexts() {
        Platform.runLater(() -> {
            // Проверяем на null перед использованием
            if (languageMenu != null) languageMenu.setText(getLocalizedText("language"));
            if (russianMenuItem != null) russianMenuItem.setText(getLocalizedText("russian"));
            if (icelandMenuItem != null) icelandMenuItem.setText(getLocalizedText("icelandic"));
            if (lithuanianMenuItem != null) lithuanianMenuItem.setText(getLocalizedText("lithuanian"));
            if (mexicanMenuItem != null) mexicanMenuItem.setText(getLocalizedText("mexican_spanish"));

            if (endSessionButton != null) endSessionButton.setText(getLocalizedText("logout"));
            if (showUserNicknameText != null) showUserNicknameText.setText(getLocalizedText("user") + ": " + GuiApp.username);

            if (tableTab != null) tableTab.setText(getLocalizedText("table_view"));
            if (vizuslisationTab != null) vizuslisationTab.setText(getLocalizedText("visualization"));

            // Обновляем заголовки колонок
            updateColumnText(idTableColumn, "id");
            updateColumnText(ownerTableColumn, "owner");
            updateColumnText(filmNameTableColumn, "film_name");
            updateColumnText(coodinatesTableColumn, "coordinates");
            updateColumnText(xCoordinatesTableColumn, "X");
            updateColumnText(yCoordinatesTableColumn, "Y");
            updateColumnText(creationDateTableColumn, "creation_date");
            updateColumnText(oscarCountTableColumn, "oscars");
            updateColumnText(genreTableColumn, "genre");
            updateColumnText(mpaaRatingTableColumn, "restriction");
            updateColumnText(operatorTableColumn, "operator");
            updateColumnText(nameOperatorTableColumn, "name");
            updateColumnText(heightOperatorTableColumn, "height");
            updateColumnText(eyeColorOperatorTableColumn, "eye_color");
            updateColumnText(hairColorOperatorTableColumn, "hair_color");
            updateColumnText(countryOperatorTableColumn, "country");
            updateColumnText(locationOperatorTableColumn, "location");
            updateColumnText(xLocationOperatorTableColumn, "X");
            updateColumnText(yLocationOperatorTableColumn, "Y");
            updateColumnText(zLocationOperatorTableColumn, "Z");
            if (commandOCHKA != null) commandOCHKA.setText(getLocalizedText("command"));
            if (help != null) help.setText(getLocalizedText("help"));
            if (info != null) info.setText(getLocalizedText("info"));
            if (add != null) add.setText(getLocalizedText("add"));
            if (remove_by_id != null) remove_by_id.setText(getLocalizedText("remove_by_id"));
            if (remove_first != null) remove_first.setText(getLocalizedText("remove_first"));
            if (remove_lower != null) remove_lower.setText(getLocalizedText("remove_lower"));
            if (execute_script != null) execute_script.setText(getLocalizedText("execute_script"));
        });
    }

    // Вспомогательный метод для безопасного обновления текста колонки
    private void updateColumnText(TableColumn<?, ?> column, String key) {
        if (column != null) {
            column.setText(getLocalizedText(key));
        }
    }

    private String getLocalizedText(String key) {
        try {
            return currentBundle.getString(key);
        } catch (MissingResourceException e) {
            return "!" + key + "!"; // Возвращаем ключ, чтобы было видно, чего не хватает
        }
    }

    private void setupTableColumns() {
        // Проверка уже сделана в initialize, но для надежности можно оставить
        if (tableView == null) return;

        // Привязка данных к колонкам (CellValueFactory)
        idTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        ownerTableColumn.setCellValueFactory(cellData -> cellData.getValue().ownerProperty());
        filmNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().filmNameProperty());
        xCoordinatesTableColumn.setCellValueFactory(cellData -> cellData.getValue().xCoordinateProperty());
        yCoordinatesTableColumn.setCellValueFactory(cellData -> cellData.getValue().yCoordinateProperty());
        creationDateTableColumn.setCellValueFactory(cellData -> cellData.getValue().creationDateProperty());
        oscarCountTableColumn.setCellValueFactory(cellData -> cellData.getValue().oscarCountProperty());
        genreTableColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        mpaaRatingTableColumn.setCellValueFactory(cellData -> cellData.getValue().mpaaRatingProperty());
        nameOperatorTableColumn.setCellValueFactory(cellData -> cellData.getValue().operatorNameProperty());
        heightOperatorTableColumn.setCellValueFactory(cellData -> cellData.getValue().operatorHeightProperty());
        eyeColorOperatorTableColumn.setCellValueFactory(cellData -> cellData.getValue().operatorEyeColorProperty());
        hairColorOperatorTableColumn.setCellValueFactory(cellData -> cellData.getValue().operatorHairColorProperty());
        countryOperatorTableColumn.setCellValueFactory(cellData -> cellData.getValue().operatorCountryProperty());
        xLocationOperatorTableColumn.setCellValueFactory(cellData -> cellData.getValue().operatorLocationXProperty());
        yLocationOperatorTableColumn.setCellValueFactory(cellData -> cellData.getValue().operatorLocationYProperty());
        zLocationOperatorTableColumn.setCellValueFactory(cellData -> cellData.getValue().operatorLocationZProperty());
    }

    private void setupMenuHandlers() {
        if (russianMenuItem != null) russianMenuItem.setOnAction(e -> changeLanguage(new Locale("ru")));
        if (icelandMenuItem != null) icelandMenuItem.setOnAction(e -> changeLanguage(new Locale("is")));
        if (lithuanianMenuItem != null) lithuanianMenuItem.setOnAction(e -> changeLanguage(new Locale("lt")));
        if (mexicanMenuItem != null) mexicanMenuItem.setOnAction(e -> changeLanguage(new Locale("es", "MX")));
        if (endSessionButton != null) endSessionButton.setOnAction(e -> {
            try {
                logout();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Обработчики для команд
        if (help != null) help.setOnAction(e -> handleHelpCommand());
        if (info != null) info.setOnAction(e -> handleInfoCommand());
        if (add != null) add.setOnAction(e -> handleAddCommand());
        if (remove_by_id != null) remove_by_id.setOnAction(e -> handleRemoveByIdCommand());
        if (remove_first != null) remove_first.setOnAction(e -> handleRemoveFirstCommand());
        if (remove_lower != null) remove_lower.setOnAction(e -> handleRemoveLowerCommand());
        if (execute_script != null) execute_script.setOnAction(e -> handleExecuteScriptCommand());
    }

    private void changeLanguage(Locale locale) {
        GuiApp.setCurrentLocale(locale);
        updateLocalization();
        if (tableView != null) tableView.refresh();
    }

    private void logout() throws IOException {
        GuiApp.authorized = false;
        GuiApp.username = "";
        if (endSessionButton != null) {
            Stage stage = (Stage) endSessionButton.getScene().getWindow();
            stage.close();
        }
        AuthApplication app = new AuthApplication();
        app.start(new Stage());
    }

    private void setupVisualization() {
        if (vizualCanvas != null) {
            GraphicsContext gc = vizualCanvas.getGraphicsContext2D();
            gc.setFill(Color.LIGHTBLUE);
            gc.fillRect(0, 0, vizualCanvas.getWidth(), vizualCanvas.getHeight());
            gc.setFill(Color.BLACK);
            gc.fillText("Visualization will be implemented here", 50, 50);
        }
    }

    private void loadData() {
        CommandBuffer message = new CommandBuffer("show");
        try {
            ServerAnswerBuffer answerBuffer = GuiApp.udpClient.sendCommand(message);
            String csvData = answerBuffer.getComment();
            parseAndLoadData(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseAndLoadData(String csvData) {
        // Используем movieDataList, который уже привязан к таблице
        movieDataList.clear(); // Очищаем старые данные

        String[] lines = csvData.trim().split("\n");

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] values = line.split(",");
            if (values.length >= 17) {
                // Заменяем "null" строки на пустые строки
                for (int i = 0; i < values.length; i++) {
                    if ("null".equalsIgnoreCase(values[i].trim())) {
                        values[i] = "";
                    }
                }
                MovieData movie = new MovieData(
                        values[0], values[1], values[2], values[3], values[4],
                        values[5], values[6], values[7], values[8], values[9],
                        values[10], values[11], values[12], values[13], values[14],
                        values[15], values[16]
                );
                movieDataList.add(movie);
            }
        }
        System.out.println("Данные успешно загружены. Загружено объектов: " + movieDataList.size());
    }

    private void handleAddCommand() {
        System.out.println("add");
        Optional<Movie> movieResult = MovieInputApp.showMovieInputDialog();

        if (movieResult.isPresent()) {
            Movie movie = movieResult.get();

            try {
                CommandBuffer addCommand = new CommandBuffer("add");
                movie.setUserLogin(GuiApp.username);
                addCommand.setLogin(GuiApp.username);
                addCommand.setPassword(GuiApp.username);
                addCommand.setMovie(movie);
                ServerAnswerBuffer response = GuiApp.udpClient.sendCommand(addCommand);

                if (response.getAnswerStatus() == AnswerStatus.OK) {
                    // Успешно добавлен, обновляем таблицу
                    loadData();
                    showSuccessMessage("Фильм успешно добавлен!");
                } else {
                    showErrorMessage("Ошибка при добавлении фильма: " + response.getComment());
                }

            } catch (IOException | ClassNotFoundException e) {
                showErrorMessage("Ошибка соединения с сервером: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Добавление фильма отменено пользователем");
        }
    }

    // Добавьте эти вспомогательные методы в MainController
    private void showSuccessMessage(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    private void showErrorMessage(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    private void editMovie(MovieData movieData) {
        try {
            // Создаем объект Movie из данных таблицы
            Movie movieToEdit = parseMovieFromTableData(movieData);

            // Открываем окно редактирования с текущими данными
            Optional<Movie> editedMovie = MovieInputApp.showMovieEditDialog(movieToEdit);

            if (editedMovie.isPresent()) {
                // Отправляем команду обновления на сервер
                CommandBuffer updateCommand = new CommandBuffer("update");

                updateCommand.setLogin(GuiApp.username);
                updateCommand.setPassword(GuiApp.username);
                Movie movie = editedMovie.get();
                movie.setUserLogin(GuiApp.username);
                updateCommand.setMovie(movie);
                updateCommand.setArg(String.valueOf(Integer.parseInt(movieData.idProperty().get())));

                ServerAnswerBuffer updateResponse = GuiApp.udpClient.sendCommand(updateCommand);

                if (updateResponse.getAnswerStatus() == AnswerStatus.OK) {
                    loadData(); // Обновляем таблицу
                    showSuccessMessage(getLocalizedText("movie_updated"));
                } else {
                    showErrorMessage(updateResponse.getComment());
                }
            }
        } catch (Exception e) {
            showErrorMessage(getLocalizedText("edit_error") + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Movie parseMovieFromTableData(MovieData data) {
        Movie movie = new Movie();

        // Основные поля
        movie.setName(data.filmNameProperty().get());

        // Координаты
        Coordinates coordinates = new Coordinates();
        coordinates.setX(Long.parseLong(data.xCoordinateProperty().get()));
        coordinates.setY(Long.parseLong(data.yCoordinateProperty().get()));
        movie.setCoordinates(coordinates);

        // Оскары (может быть null)
        String oscars = data.oscarCountProperty().get();
        if (!oscars.isEmpty()) {
            movie.setOscarsCount(Long.parseLong(oscars));
        }

        // Жанр (может быть null)
        String genre = data.genreProperty().get();
        if (!genre.isEmpty()) {
            movie.setGenre(MovieGenre.valueOf(genre));
        }

        // MPAA рейтинг
        movie.setMpaaRating(MpaaRating.valueOf(data.mpaaRatingProperty().get()));

        // Оператор (может быть null)
        String operatorName = data.operatorNameProperty().get();
        if (!operatorName.isEmpty()) {
            Person operator = new Person();
            operator.setName(operatorName);
            operator.setHeight(Integer.parseInt(data.operatorHeightProperty().get()));
            operator.setHairColor(HairColor.valueOf(data.operatorHairColorProperty().get()));

            // Цвет глаз (может быть null)
            String eyeColor = data.operatorEyeColorProperty().get();
            if (!eyeColor.isEmpty()) {
                operator.setEyeColor(EyeColor.valueOf(eyeColor));
            }

            // Страна (может быть null)
            String country = data.operatorCountryProperty().get();
            if (!country.isEmpty()) {
                operator.setNationality(Country.valueOf(country));
            }

            // Локация (может быть null)
            String xLoc = data.operatorLocationXProperty().get();
            String yLoc = data.operatorLocationYProperty().get();
            String zLoc = data.operatorLocationZProperty().get();

            if (!xLoc.isEmpty() && !yLoc.isEmpty() && !zLoc.isEmpty()) {
                Location location = new Location();
                location.setX(Long.parseLong(xLoc));
                location.setY(Double.parseDouble(yLoc));
                location.setZ(Long.parseLong(zLoc));
                operator.setLocation(location);
            }

            movie.setOperator(operator);
        }

        return movie;
    }
    private void handleHelpCommand() {
        try {
            CommandBuffer command = new CommandBuffer("help");
            command.setLogin(GuiApp.username);
            command.setPassword(GuiApp.username);
            ServerAnswerBuffer response = GuiApp.udpClient.sendCommand(command);

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(getLocalizedText("help"));
                alert.setHeaderText(getLocalizedText("available_commands"));
                alert.setContentText(response.getComment());
                alert.showAndWait();
            });
        } catch (IOException | ClassNotFoundException e) {
            showErrorMessage("Error getting help: " + e.getMessage());
        }
    }

    private void handleInfoCommand() {
        try {
            CommandBuffer command = new CommandBuffer("info");
            command.setLogin(GuiApp.username);
            command.setPassword(GuiApp.username);
            ServerAnswerBuffer response = GuiApp.udpClient.sendCommand(command);

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(getLocalizedText("info"));
                alert.setHeaderText(getLocalizedText("collection_info"));
                alert.setContentText(response.getComment());
                alert.showAndWait();
            });
        } catch (IOException | ClassNotFoundException e) {
            showErrorMessage("Error getting info: " + e.getMessage());
        }
    }

    private void handleRemoveByIdCommand() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(getLocalizedText("remove_by_id"));
        dialog.setHeaderText(getLocalizedText("enter_id"));
        dialog.setContentText("ID:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(id -> {
            try {
                CommandBuffer command = new CommandBuffer("remove_by_id");
                command.setLogin(GuiApp.username);
                command.setPassword(GuiApp.username);
                command.setArg(id);
                ServerAnswerBuffer response = GuiApp.udpClient.sendCommand(command);

                if (response.getAnswerStatus() == AnswerStatus.OK) {
                    loadData();
                    showSuccessMessage(getLocalizedText("movie_removed"));
                } else {
                    showErrorMessage(getLocalizedText("error") + ": " + response.getComment());
                }
            } catch (IOException | ClassNotFoundException e) {
                showErrorMessage(getLocalizedText("error") + ": " + e.getMessage());
            }
        });
    }

    private void handleRemoveFirstCommand() {
        try {
            CommandBuffer command = new CommandBuffer("remove_first");
            command.setLogin(GuiApp.username);
            command.setPassword(GuiApp.username);
            ServerAnswerBuffer response = GuiApp.udpClient.sendCommand(command);

            if (response.getAnswerStatus() == AnswerStatus.OK) {
                loadData();
                showSuccessMessage(getLocalizedText("first_movie_removed"));
            } else {
                showErrorMessage(getLocalizedText("error") + ": " + response.getComment());
            }
        } catch (IOException | ClassNotFoundException e) {
            showErrorMessage(getLocalizedText("error") + ": " + e.getMessage());
        }
    }

    private void handleRemoveLowerCommand() {
        Optional<Movie> movieResult = MovieInputApp.showMovieInputDialog();

        if (movieResult.isPresent()) {
            Movie movie = movieResult.get();
            try {
                CommandBuffer command = new CommandBuffer("remove_lower");
                command.setLogin(GuiApp.username);
                command.setPassword(GuiApp.username);
                command.setMovie(movie);
                ServerAnswerBuffer response = GuiApp.udpClient.sendCommand(command);

                if (response.getAnswerStatus() == AnswerStatus.OK) {
                    loadData();
                    showSuccessMessage(getLocalizedText("lower_movies_removed"));
                } else {
                    showErrorMessage(getLocalizedText("error") + ": " + response.getComment());
                }
            } catch (IOException | ClassNotFoundException e) {
                showErrorMessage(getLocalizedText("error") + ": " + e.getMessage());
            }
        }
    }

    private void handleExecuteScriptCommand() {
        Stage stage = (Stage) menuBar.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(getLocalizedText("select_script"));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                String scriptPath = file.getAbsolutePath();
                CommandBuffer command = new CommandBuffer("execute_script");
                command.setLogin(GuiApp.username);
                command.setPassword(GuiApp.username);
                command.setArg(scriptPath);
                ServerAnswerBuffer response = GuiApp.udpClient.sendCommand(command);

                if (response.getAnswerStatus() == AnswerStatus.OK) {
                    showSuccessMessage(getLocalizedText("script_executed"));
                    loadData();
                } else {
                    showErrorMessage(getLocalizedText("error") + ": " + response.getComment());
                }
            } catch (IOException | ClassNotFoundException e) {
                showErrorMessage(getLocalizedText("error") + ": " + e.getMessage());
            }
        }
    }

    // Внутренний класс для хранения данных одного фильма
    public static class MovieData {
        private final SimpleStringProperty id, owner, filmName, xCoordinate, yCoordinate, creationDate, oscarCount, genre, mpaaRating, operatorName, operatorHeight, operatorEyeColor, operatorHairColor, operatorCountry, operatorLocationX, operatorLocationY, operatorLocationZ;

        public MovieData(String id, String owner, String filmName, String xCoordinate, String yCoordinate,
                         String creationDate, String oscarCount, String genre, String mpaaRating,
                         String operatorName, String operatorHeight, String operatorEyeColor,
                         String operatorHairColor, String operatorCountry, String operatorLocationX,
                         String operatorLocationY, String operatorLocationZ) {
            this.id = new SimpleStringProperty(id);
            this.owner = new SimpleStringProperty(owner);
            this.filmName = new SimpleStringProperty(filmName);
            this.xCoordinate = new SimpleStringProperty(xCoordinate);
            this.yCoordinate = new SimpleStringProperty(yCoordinate);
            this.creationDate = new SimpleStringProperty(creationDate);
            this.oscarCount = new SimpleStringProperty(oscarCount);
            this.genre = new SimpleStringProperty(genre);
            this.mpaaRating = new SimpleStringProperty(mpaaRating);
            this.operatorName = new SimpleStringProperty(operatorName);
            this.operatorHeight = new SimpleStringProperty(operatorHeight);
            this.operatorEyeColor = new SimpleStringProperty(operatorEyeColor);
            this.operatorHairColor = new SimpleStringProperty(operatorHairColor);
            this.operatorCountry = new SimpleStringProperty(operatorCountry);
            this.operatorLocationX = new SimpleStringProperty(operatorLocationX);
            this.operatorLocationY = new SimpleStringProperty(operatorLocationY);
            this.operatorLocationZ = new SimpleStringProperty(operatorLocationZ);
        }

        // Property-геттеры для привязки к TableView
        public SimpleStringProperty idProperty() { return id; }
        public SimpleStringProperty ownerProperty() { return owner; }
        public SimpleStringProperty filmNameProperty() { return filmName; }
        public SimpleStringProperty xCoordinateProperty() { return xCoordinate; }
        public SimpleStringProperty yCoordinateProperty() { return yCoordinate; }
        public SimpleStringProperty creationDateProperty() { return creationDate; }
        public SimpleStringProperty oscarCountProperty() { return oscarCount; }
        public SimpleStringProperty genreProperty() { return genre; }
        public SimpleStringProperty mpaaRatingProperty() { return mpaaRating; }
        public SimpleStringProperty operatorNameProperty() { return operatorName; }
        public SimpleStringProperty operatorHeightProperty() { return operatorHeight; }
        public SimpleStringProperty operatorEyeColorProperty() { return operatorEyeColor; }
        public SimpleStringProperty operatorHairColorProperty() { return operatorHairColor; }
        public SimpleStringProperty operatorCountryProperty() { return operatorCountry; }
        public SimpleStringProperty operatorLocationXProperty() { return operatorLocationX; }
        public SimpleStringProperty operatorLocationYProperty() { return operatorLocationY; }
        public SimpleStringProperty operatorLocationZProperty() { return operatorLocationZ; }
    }
}