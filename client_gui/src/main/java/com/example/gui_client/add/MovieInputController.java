package com.example.gui_client.add;

import entities.classes.*;
import entities.enums.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MovieInputController implements Initializable {

    @FXML private Text introText;
    @FXML private Text nameEnterText;
    @FXML private Text coordinatesEnterText;
    @FXML private Text oscarsEnterText;
    @FXML private Text genreEnterText;
    @FXML private Text mpaaEnterText;
    @FXML private Text nameOperatorEnterText;
    @FXML private Text heightOperatorEnterText;
    @FXML private Text eyeColorOperatorEnterText;
    @FXML private Text hairColorOperatorEnterText;
    @FXML private Text countryOperatorEnterText;
    @FXML private Text locationOperatorEnterText;

    @FXML private TextField nameEnterTextField;
    @FXML private TextField xCooedEnterTextField;
    @FXML private TextField yCoordEnterTextField;
    @FXML private TextField oscarsEnterTextField;
    @FXML private TextField nameOperatorEnterTextField;
    @FXML private TextField heightOperatorEnterTextField;
    @FXML private TextField xLocOperatorEnterTextField;
    @FXML private TextField yLocOperatorEnterTextField;
    @FXML private TextField zLocOperatorEnterTextField;

    @FXML private ChoiceBox<MovieGenre> genreChoiceBox;
    @FXML private ChoiceBox<MpaaRating> mpaaChoiceBox;
    @FXML private ChoiceBox<EyeColor> eyeColorOperatorChoiceBox;
    @FXML private ChoiceBox<HairColor> hairColorOperatorChoiceBox;
    @FXML private ChoiceBox<Country> countryOperatorChoiceBox;

    @FXML private CheckBox oscarCheckBox;
    @FXML private CheckBox genreCheckBox;
    @FXML private CheckBox operatorCheckBox;
    @FXML private CheckBox eyeColorCheckBox;
    @FXML private CheckBox countryCheckBox;
    @FXML private CheckBox locationCheckBox;

    private ResourceBundle bundle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bundle = resources;
        initializeChoiceBoxes();
        setupEventHandlers();
        updateLocalization();
        setInitialFieldStates();
    }

    private void initializeChoiceBoxes() {
        genreChoiceBox.getItems().addAll(MovieGenre.values());
        mpaaChoiceBox.getItems().addAll(MpaaRating.values());
        eyeColorOperatorChoiceBox.getItems().addAll(EyeColor.values());
        hairColorOperatorChoiceBox.getItems().addAll(HairColor.values());
        countryOperatorChoiceBox.getItems().addAll(Country.values());
    }

    private void setupEventHandlers() {
        oscarCheckBox.setOnAction(e -> updateOscarFields());
        genreCheckBox.setOnAction(e -> updateGenreFields());
        operatorCheckBox.setOnAction(e -> updateOperatorFields());
        eyeColorCheckBox.setOnAction(e -> updateEyeColorFields());
        countryCheckBox.setOnAction(e -> updateCountryFields());
        locationCheckBox.setOnAction(e -> updateLocationFields());
    }

    private void setInitialFieldStates() {
        updateOscarFields();
        updateGenreFields();
        updateOperatorFields();
        updateEyeColorFields();
        updateCountryFields();
        updateLocationFields();
    }

    private void updateOscarFields() {
        boolean enabled = oscarCheckBox.isSelected();
        oscarsEnterTextField.setDisable(!enabled);
        oscarsEnterText.setDisable(!enabled);
    }

    private void updateGenreFields() {
        boolean enabled = genreCheckBox.isSelected();
        genreChoiceBox.setDisable(!enabled);
        genreEnterText.setDisable(!enabled);
    }

    private void updateOperatorFields() {
        boolean enabled = operatorCheckBox.isSelected();
        nameOperatorEnterTextField.setDisable(!enabled);
        nameOperatorEnterText.setDisable(!enabled);
        heightOperatorEnterTextField.setDisable(!enabled);
        heightOperatorEnterText.setDisable(!enabled);
        hairColorOperatorChoiceBox.setDisable(!enabled);
        hairColorOperatorEnterText.setDisable(!enabled);

        if (!enabled) {
            eyeColorCheckBox.setSelected(false);
            countryCheckBox.setSelected(false);
            locationCheckBox.setSelected(false);
            updateEyeColorFields();
            updateCountryFields();
            updateLocationFields();
        }
    }

    private void updateEyeColorFields() {
        boolean enabled = eyeColorCheckBox.isSelected() && operatorCheckBox.isSelected();
        eyeColorOperatorChoiceBox.setDisable(!enabled);
        eyeColorOperatorEnterText.setDisable(!enabled);
    }

    private void updateCountryFields() {
        boolean enabled = countryCheckBox.isSelected() && operatorCheckBox.isSelected();
        countryOperatorChoiceBox.setDisable(!enabled);
        countryOperatorEnterText.setDisable(!enabled);
    }

    private void updateLocationFields() {
        boolean enabled = locationCheckBox.isSelected() && operatorCheckBox.isSelected();
        xLocOperatorEnterTextField.setDisable(!enabled);
        yLocOperatorEnterTextField.setDisable(!enabled);
        zLocOperatorEnterTextField.setDisable(!enabled);
        locationOperatorEnterText.setDisable(!enabled);
    }

    private void updateLocalization() {
        if (bundle != null) {
            introText.setText(bundle.getString("intro_text"));
            nameEnterText.setText(bundle.getString("name_text"));
            coordinatesEnterText.setText(bundle.getString("coordinates_text"));
            oscarsEnterText.setText(bundle.getString("oscars_text"));
            genreEnterText.setText(bundle.getString("genre_text"));
            mpaaEnterText.setText(bundle.getString("mpaa_text"));
            nameOperatorEnterText.setText(bundle.getString("operator_name_text"));
            heightOperatorEnterText.setText(bundle.getString("operator_height_text"));
            eyeColorOperatorEnterText.setText(bundle.getString("operator_eye_color_text"));
            hairColorOperatorEnterText.setText(bundle.getString("operator_hair_color_text"));
            countryOperatorEnterText.setText(bundle.getString("operator_country_text"));
            locationOperatorEnterText.setText(bundle.getString("operator_location_text"));

            oscarCheckBox.setText(bundle.getString("oscar_checkbox"));
            genreCheckBox.setText(bundle.getString("genre_checkbox"));
            operatorCheckBox.setText(bundle.getString("operator_checkbox"));
            eyeColorCheckBox.setText(bundle.getString("eye_color_checkbox"));
            countryCheckBox.setText(bundle.getString("country_checkbox"));
            locationCheckBox.setText(bundle.getString("location_checkbox"));
        }
    }

    public Movie createMovie() throws IllegalArgumentException {
        Movie movie = new Movie();

        // Обязательные поля
        String name = nameEnterTextField.getText().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException(bundle.getString("empty_name_err"));
        }
        movie.setName(name);

        // Координаты
        try {
            long x = Long.parseLong(xCooedEnterTextField.getText().trim());
            long y = Long.parseLong(yCoordEnterTextField.getText().trim());
            Coordinates coordinates = new Coordinates();
            coordinates.setX(x);
            coordinates.setY(y);
            if (!coordinates.check()) {
                throw new IllegalArgumentException(bundle.getString("x_coord_err"));
            }
            movie.setCoordinates(coordinates);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(bundle.getString("invalid_coordinates_err"));
        }

        // MPAA рейтинг (обязательный)
        if (mpaaChoiceBox.getValue() == null) {
            throw new IllegalArgumentException(bundle.getString("mpaa_required_err"));
        }
        movie.setMpaaRating(mpaaChoiceBox.getValue());

        // Оскары (опциональный)
        if (oscarCheckBox.isSelected()) {
            try {
                String oscarsText = oscarsEnterTextField.getText().trim();
                if (!oscarsText.isEmpty()) {
                    long oscars = Long.parseLong(oscarsText);
                    if (oscars <= 0) {
                        throw new IllegalArgumentException(bundle.getString("oscars_positive_err"));
                    }
                    movie.setOscarsCount(oscars);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(bundle.getString("invalid_oscars_format"));
            }
        }

        // Жанр (опциональный)
        if (genreCheckBox.isSelected()) {
            movie.setGenre(genreChoiceBox.getValue());
        }

        // Оператор (опциональный)
        if (operatorCheckBox.isSelected()) {
            Person operator = createPerson();
            movie.setOperator(operator);
        }
        System.out.println(movie.toCsv());
        return movie;
    }

    private Person createPerson() throws IllegalArgumentException {
        Person person = new Person();

        // Обязательные поля для Person
        String operatorName = nameOperatorEnterTextField.getText().trim();
        if (operatorName.isEmpty()) {
            throw new IllegalArgumentException(bundle.getString("empty_operator_name_err"));
        }
        person.setName(operatorName);

        try {
            int height = Integer.parseInt(heightOperatorEnterTextField.getText().trim());
            if (height <= 0) {
                throw new IllegalArgumentException(bundle.getString("height_positive_err"));
            }
            person.setHeight(height);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(bundle.getString("invalid_height_format"));
        }

        // Цвет волос (обязательный для Person)
        if (hairColorOperatorChoiceBox.getValue() == null) {
            throw new IllegalArgumentException(bundle.getString("hair_color_required_err"));
        }
        person.setHairColor(hairColorOperatorChoiceBox.getValue());

        // Цвет глаз (опциональный)
        if (eyeColorCheckBox.isSelected()) {
            person.setEyeColor(eyeColorOperatorChoiceBox.getValue());
        }

        // Страна (опциональная)
        if (countryCheckBox.isSelected()) {
            person.setNationality(countryOperatorChoiceBox.getValue());
        }

        // Локация (опциональная)
        if (locationCheckBox.isSelected()) {
            Location location = createLocation();
            person.setLocation(location);
        }

        return person;
    }

    private Location createLocation() throws IllegalArgumentException {
        Location location = new Location();

        try {
            String xText = xLocOperatorEnterTextField.getText().trim();
            String yText = yLocOperatorEnterTextField.getText().trim();
            String zText = zLocOperatorEnterTextField.getText().trim();

            if (xText.isEmpty() || yText.isEmpty() || zText.isEmpty()) {
                throw new IllegalArgumentException(bundle.getString("location_required_err"));
            }

            Long x = Long.parseLong(xText);
            Double y = Double.parseDouble(yText);
            long z = Long.parseLong(zText);

            location.setX(x);
            location.setY(y);
            location.setZ(z);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(bundle.getString("invalid_location_format"));
        }

        return location;
    }

    public void setLocale(Locale locale) {
        this.bundle = ResourceBundle.getBundle("messages", locale);
        updateLocalization();
    }
}