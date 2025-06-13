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
import javafx.animation.FadeTransition;
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
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

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

    @FXML private TableView<MovieData> tableView;
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
    private final Map<String, Color> userColors = new HashMap<>();
    private final Random random = new Random();
    private final Map<Integer, MovieData> movieMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (tableView != null) {
            tableView.setItems(movieDataList);
        } else {
            System.err.println("CRITICAL ERROR: tableView is null. Check fx:id in main.fxml!");
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

    private void setupVisualization() {
        if (vizualCanvas != null) {
            GraphicsContext gc = vizualCanvas.getGraphicsContext2D();
            gc.clearRect(0, 0, vizualCanvas.getWidth(), vizualCanvas.getHeight());

            vizualCanvas.setOnMouseClicked(event -> {
                double x = event.getX();
                double y = event.getY();

                for (MovieData movie : movieDataList) {
                    try {
                        int id = Integer.parseInt(movie.idProperty().get());
                        double size = getSizeFromMpaa(movie.mpaaRatingProperty().get());
                        double movieX = Double.parseDouble(movie.xCoordinateProperty().get());
                        double movieY = Double.parseDouble(movie.yCoordinateProperty().get());

                        double canvasX = normalizeCoordinate(movieX, vizualCanvas.getWidth(), size);
                        double canvasY = normalizeCoordinate(movieY, vizualCanvas.getHeight(), size);

                        if (x >= canvasX && x <= canvasX + size &&
                                y >= canvasY && y <= canvasY + size) {
                            showMovieInfo(movie);
                            break;
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
            });
        }
    }

    private double normalizeCoordinate(double value, double max, double size) {
        return (value % (max - size));
    }

    private double getSizeFromMpaa(String mpaaRating) {
        switch (mpaaRating) {
            case "PG": return 30;
            case "PG_13": return 50;
            case "R": return 70;
            case "NC_17": return 90;
            default: return 30;
        }
    }

    private Color getUserColor(String username) {
        if (!userColors.containsKey(username)) {
            userColors.put(username, Color.color(
                    random.nextDouble(),
                    random.nextDouble(),
                    random.nextDouble(),
                    0.7
            ));
        }
        return userColors.get(username);
    }

    private void drawMovies() {
        if (vizualCanvas == null) return;

        GraphicsContext gc = vizualCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, vizualCanvas.getWidth(), vizualCanvas.getHeight());

        List<MovieData> sortedMovies = movieDataList.stream()
                .sorted(Comparator.comparing(m -> m.mpaaRatingProperty().get()))
                .collect(Collectors.toList());

        for (MovieData movie : sortedMovies) {
            try {
                String mpaa = movie.mpaaRatingProperty().get();
                double size = getSizeFromMpaa(mpaa);
                double x = Double.parseDouble(movie.xCoordinateProperty().get());
                double y = Double.parseDouble(movie.yCoordinateProperty().get());
                String owner = movie.ownerProperty().get();
                Color color = getUserColor(owner);

                double canvasX = normalizeCoordinate(x, vizualCanvas.getWidth(), size);
                double canvasY = normalizeCoordinate(y, vizualCanvas.getHeight(), size);

                gc.setFill(color);
                gc.fillRect(canvasX, canvasY, size, size);

                gc.setStroke(Color.BLACK);
                gc.setLineWidth(1);
                gc.strokeRect(canvasX, canvasY, size, size);

                FadeTransition ft = new FadeTransition(Duration.millis(10000), vizualCanvas);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.play();

            } catch (NumberFormatException e) {
                continue;
            }
        }
    }

    private void showMovieInfo(MovieData movie) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(movie.filmNameProperty().get());
        alert.setHeaderText("Movie Details");

        StringBuilder content = new StringBuilder();
        content.append("ID: ").append(movie.idProperty().get()).append("\n");
        content.append("Owner: ").append(movie.ownerProperty().get()).append("\n");
        content.append("Name: ").append(movie.filmNameProperty().get()).append("\n");
        content.append("Coordinates: (").append(movie.xCoordinateProperty().get())
                .append(", ").append(movie.yCoordinateProperty().get()).append(")\n");
        content.append("MPAA Rating: ").append(movie.mpaaRatingProperty().get()).append("\n");
        content.append("Genre: ").append(movie.genreProperty().get()).append("\n");

        alert.setContentText(content.toString());
        alert.showAndWait();
    }

    private void parseAndLoadData(String csvData) {
        movieDataList.clear();
        movieMap.clear();

        String[] lines = csvData.trim().split("\n");

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] values = line.split(",");
            if (values.length >= 17) {
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
                try {
                    movieMap.put(Integer.parseInt(values[0]), movie);
                } catch (NumberFormatException e) {
                    // Пропускаем некорректные ID
                }
            }
        }

        Platform.runLater(this::drawMovies);
    }

    private void setupTableContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem editItem = new MenuItem(getLocalizedText("edit"));
        editItem.setOnAction(event -> handleEditAction());

        MenuItem removeItem = new MenuItem(getLocalizedText("remove"));
        removeItem.setOnAction(event -> handleRemoveAction());

        contextMenu.getItems().addAll(editItem, removeItem);
        tableView.setContextMenu(contextMenu);

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

    private void handleRemoveAction() {
        MovieData selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (selected.ownerProperty().get().equals(GuiApp.username)) {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setTitle(getLocalizedText("confirmation"));
                confirmation.setHeaderText(getLocalizedText("confirm_remove"));
                confirmation.setContentText(getLocalizedText("remove_movie") + " ID: " + selected.idProperty().get());

                Optional<ButtonType> result = confirmation.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    try {
                        CommandBuffer command = new CommandBuffer("remove_by_id");
                        command.setLogin(GuiApp.username);
                        command.setPassword(GuiApp.username);
                        command.setArg(selected.idProperty().get());
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
                }
            } else {
                showErrorMessage(getLocalizedText("remove_own_only_err"));
            }
        }
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
            if (languageMenu != null) languageMenu.setText(getLocalizedText("language"));
            if (russianMenuItem != null) russianMenuItem.setText(getLocalizedText("russian"));
            if (icelandMenuItem != null) icelandMenuItem.setText(getLocalizedText("icelandic"));
            if (lithuanianMenuItem != null) lithuanianMenuItem.setText(getLocalizedText("lithuanian"));
            if (mexicanMenuItem != null) mexicanMenuItem.setText(getLocalizedText("mexican_spanish"));

            if (endSessionButton != null) endSessionButton.setText(getLocalizedText("logout"));
            if (showUserNicknameText != null) showUserNicknameText.setText(getLocalizedText("user") + ": " + GuiApp.username);

            if (tableTab != null) tableTab.setText(getLocalizedText("table_view"));
            if (vizuslisationTab != null) vizuslisationTab.setText(getLocalizedText("visualization"));

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
            MenuItem editItem = new MenuItem(getLocalizedText("edit"));
            editItem.setOnAction(event -> handleEditAction());

            MenuItem removeItem = new MenuItem(getLocalizedText("remove"));
            removeItem.setOnAction(event -> handleRemoveAction());
        });
    }

    private void updateColumnText(TableColumn<?, ?> column, String key) {
        if (column != null) {
            column.setText(getLocalizedText(key));
        }
    }

    private String getLocalizedText(String key) {
        try {
            return currentBundle.getString(key);
        } catch (MissingResourceException e) {
            return "!" + key + "!";
        }
    }

    private void setupTableColumns() {
        if (tableView == null) return;

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

    private void loadData() {
        CommandBuffer message = new CommandBuffer("show");
        try {
            ServerAnswerBuffer answerBuffer = GuiApp.udpClient.sendCommand(message);
            String csvData = answerBuffer.getComment();
            parseAndLoadData(csvData);
        } catch (IOException | ClassNotFoundException e) {
            showErrorMessage("Error loading data: " + e.getMessage());
        }
    }

    private void handleAddCommand() {
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
                    loadData();
                    showSuccessMessage("Movie added successfully!");
                } else {
                    showErrorMessage("Error adding movie: " + response.getComment());
                }

            } catch (IOException | ClassNotFoundException e) {
                showErrorMessage("Connection error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void showSuccessMessage(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    private void showErrorMessage(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    private void editMovie(MovieData movieData) {
        try {
            Movie movieToEdit = parseMovieFromTableData(movieData);
            Optional<Movie> editedMovie = MovieInputApp.showMovieEditDialog(movieToEdit);

            if (editedMovie.isPresent()) {
                CommandBuffer updateCommand = new CommandBuffer("update");
                updateCommand.setLogin(GuiApp.username);
                updateCommand.setPassword(GuiApp.username);
                Movie movie = editedMovie.get();
                movie.setUserLogin(GuiApp.username);
                updateCommand.setMovie(movie);
                updateCommand.setArg(String.valueOf(Integer.parseInt(movieData.idProperty().get())));

                ServerAnswerBuffer updateResponse = GuiApp.udpClient.sendCommand(updateCommand);

                if (updateResponse.getAnswerStatus() == AnswerStatus.OK) {
                    loadData();
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
        movie.setName(data.filmNameProperty().get());

        Coordinates coordinates = new Coordinates();
        coordinates.setX(Long.parseLong(data.xCoordinateProperty().get()));
        coordinates.setY(Long.parseLong(data.yCoordinateProperty().get()));
        movie.setCoordinates(coordinates);

        String oscars = data.oscarCountProperty().get();
        if (!oscars.isEmpty()) {
            movie.setOscarsCount(Long.parseLong(oscars));
        }

        String genre = data.genreProperty().get();
        if (!genre.isEmpty()) {
            movie.setGenre(MovieGenre.valueOf(genre));
        }

        movie.setMpaaRating(MpaaRating.valueOf(data.mpaaRatingProperty().get()));

        String operatorName = data.operatorNameProperty().get();
        if (!operatorName.isEmpty()) {
            Person operator = new Person();
            operator.setName(operatorName);
            operator.setHeight(Integer.parseInt(data.operatorHeightProperty().get()));
            operator.setHairColor(HairColor.valueOf(data.operatorHairColorProperty().get()));

            String eyeColor = data.operatorEyeColorProperty().get();
            if (!eyeColor.isEmpty()) {
                operator.setEyeColor(EyeColor.valueOf(eyeColor));
            }

            String country = data.operatorCountryProperty().get();
            if (!country.isEmpty()) {
                operator.setNationality(Country.valueOf(country));
            }

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

    public static class MovieData {
        private final SimpleStringProperty id, owner, filmName, xCoordinate, yCoordinate, creationDate,
                oscarCount, genre, mpaaRating, operatorName, operatorHeight, operatorEyeColor,
                operatorHairColor, operatorCountry, operatorLocationX, operatorLocationY, operatorLocationZ;

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