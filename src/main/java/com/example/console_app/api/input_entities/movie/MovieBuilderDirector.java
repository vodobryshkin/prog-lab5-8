package com.example.console_app.api.input_entities.movie;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input_entities.ask.AskedResponseDirector;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.entities.classes.Movie;

/**
 * MovieBuilderDirector управляет процессом получения фильма.
 * Использует Builder и InputManager для ввода данных, взаимодействуя с пользователем
 * и обрабатывая возможные ошибки ввода.
 * 
 * Автор: Добрышкин Владимир (vodobryshkin)
 * Версия: 1.0
 * Дата: 2025-19-02
 */
public class MovieBuilderDirector {
    private InputManager inputManager;
    private Builder movieBuilder;
    
    /**
     * Конструктор класса MovieBuilderDirector.
     * 
     * @param inputManager менеджер ввода.
     */
    public MovieBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        movieBuilder = new Builder(this.inputManager);
    }

    /**
     * Организует вход фильма.
     * 
     * @throws IncorrectInputException при некорректном вводе.
     */
    public Movie inputMovie() throws IncorrectInputException {
        movieBuilder.reset();

        String typeNameString = inputManager.toString();
        movieBuilder.inputName();
        if (!typeNameString.equals(inputManager.toString())) {
            return null;
        }

        String typeCoordinatesString = inputManager.toString();
        movieBuilder.inputCoordinates();
        if (!typeCoordinatesString.equals(inputManager.toString())) {
            return null;
        }

        String typeOscarsCountString = inputManager.toString();
        AskedResponseDirector askedResponseDirector = new AskedResponseDirector("Поле oscarsCount.\n", inputManager);
        boolean responseOscarsCount = askedResponseDirector.getResponse();

        if (!responseOscarsCount && !typeOscarsCountString.equals(inputManager.toString())) {
            return null;
        }
        if (responseOscarsCount) {
            movieBuilder.inputOscarsCount();
            if (!typeOscarsCountString.equals(inputManager.toString())) {
                return null;
            }
        }

        String typeGenreString = inputManager.toString();
        askedResponseDirector = new AskedResponseDirector("Поле genre.\n", inputManager);
        boolean responseGenre = askedResponseDirector.getResponse();

        if (!responseGenre && !typeGenreString.equals(inputManager.toString())) {
            return null;
        }
        if (responseGenre) {
            movieBuilder.inputGenre();
            if (!typeGenreString.equals(inputManager.toString())) {
                return null;
            }
        }

        String typeMpaaRatingString = inputManager.toString();
        movieBuilder.inputMpaaRating();
        if (!typeMpaaRatingString.equals(inputManager.toString())) {
            return null;
        }

        String typeOperatorString = inputManager.toString();
        askedResponseDirector = new AskedResponseDirector("Поле operator.\n", inputManager);
        boolean responseOperator = askedResponseDirector.getResponse();

        if (!responseOperator && !typeOperatorString.equals(inputManager.toString())) {
            return null;
        }
        if (responseOperator) {
            movieBuilder.inputOperator();
            if (!typeOperatorString.equals(inputManager.toString())) {
                return null;
            }
        }

        return movieBuilder.getMovie();
    }
    
}
