package com.example.api.input_entities.movie;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.movie.coordinates.CoordinatesBuilderDirector;
import com.example.api.input_entities.movie.genre.GenreBuilderDirector;
import com.example.api.input_entities.movie.mpaa_rating.MpaaRatingBuilderDirector;
import com.example.api.input_entities.movie.name.NameBuilderDirector;
import com.example.api.input_entities.movie.operator.OperatorBuilderDirector;
import com.example.api.input_entities.movie.oscars_count.OscarsCountBuilderDirector;
import com.example.api.input_entities.types_builder.MovieBuilder;
import entities.classes.Movie;

/**
 * Класс Builder реализует интерфейс MovieBuilder,
 * обеспечивая создание и валидацию фильмов.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class Builder implements MovieBuilder {
    private Movie movie;
    private InputManager inputManager;
    private NameBuilderDirector nameBuilderDirector;
    private CoordinatesBuilderDirector coordinatesBuilderDirector;
    private OscarsCountBuilderDirector oscarsCountBuilderDirector;
    private GenreBuilderDirector genreBuilderDirector;
    private MpaaRatingBuilderDirector mpaaRatingBuilderDirector;
    private OperatorBuilderDirector operatorBuilderDirector;

    /**
     * Конструктор класса Builder.
     * 
     * @param inputManager менеджер ввода.
     */
    public Builder(InputManager inputManager) {
        this.inputManager = inputManager;
        movie = new Movie();

        nameBuilderDirector = new NameBuilderDirector(this.inputManager);
        coordinatesBuilderDirector = new CoordinatesBuilderDirector(this.inputManager);
        oscarsCountBuilderDirector = new OscarsCountBuilderDirector(this.inputManager);
        genreBuilderDirector = new GenreBuilderDirector(this.inputManager);
        mpaaRatingBuilderDirector = new MpaaRatingBuilderDirector(this.inputManager);
        operatorBuilderDirector = new OperatorBuilderDirector(this.inputManager);
    }

    /**
     * Сбрасывает текущее значение Movie на null.
     */
    @Override
    public void reset() {
        movie = new Movie();
        movie.setId(Movie.nextId++);
    }

    /**
     * Ввод названия фильма.
     */
    @Override
    public void inputName() throws IncorrectInputException {
        movie.setName(nameBuilderDirector.getName());
    }

    /**
     * Ввод координат.
     */
    @Override
    public void inputCoordinates() throws IncorrectInputException {
        movie.setCoordinates(coordinatesBuilderDirector.getCoordinates());
    }

    /**
     * Ввод количества Оскаров.
     */
    @Override
    public void inputOscarsCount() throws IncorrectInputException {
        movie.setOscarsCount(oscarsCountBuilderDirector.getOscarsCount());
    }

    /**
     * Ввод жанра фильма.
     */
    @Override
    public void inputGenre() throws IncorrectInputException {
        movie.setGenre(genreBuilderDirector.getMovieGenre());
    }

    /**
     * Ввод рейтинга МРАА.
     */
    @Override
    public void inputMpaaRating() throws IncorrectInputException {
        movie.setMpaaRating(mpaaRatingBuilderDirector.getMpaaRating());
    }

    /**
     * Ввод информации об опараторе.
     */
    @Override
    public void inputOperator() throws IncorrectInputException {
        movie.setOperator(operatorBuilderDirector.getOperator());
    }

    /**
     * Геттер для фильма.
     * 
     * @Movie фильм, который вводят.
     */
    public Movie getMovie() {
        return movie;
    }
}
