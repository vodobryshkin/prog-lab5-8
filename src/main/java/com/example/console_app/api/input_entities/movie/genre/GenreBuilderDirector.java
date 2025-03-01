package com.example.console_app.api.input_entities.movie.genre;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input.classes.input_strategies.KeyboardInput;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.entities.enums.MovieGenre;

/**
 * Класс GenreBuilderDirector управляет процессом получения и валидации жанра фильма.
 * Использует InputManager для чтения ввода и GenreBuilder для обработки и хранения значения.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class GenreBuilderDirector {
    private InputManager inputManager;
    private GenreBuilder genreBuilder;

    /**
     * Конструктор класса.
     *
     * @param inputManager менеджер ввода, используемый для чтения значения жанра фильма.
     */
    public GenreBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        genreBuilder = new GenreBuilder();
    }

    /**
     * Получает значение жанра фильма от пользователя.
     *
     * @return значение жанра фильма, если ввод корректен, иначе null.
     * @throws IncorrectInputException если ввод некорректен.
     */
    public MovieGenre getMovieGenre() throws IncorrectInputException {
        genreBuilder.reset();

        try {
            if (!inputManager.toString().equals("FileInput")) {
                System.out.println("Введите поле genre (доступные варианты: ACTION, WESTERN, SCIENCE_FICTION).");
            }
            genreBuilder.setValue(inputManager.readNext());
            return genreBuilder.getMovieGenre();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return null;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getMovieGenre();
            }
        }
    }
}