package com.example.console_app.api.input_entities.movie.coordinates;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.entities.classes.Coordinates;

/**
 * Класс CoordinatesBuilderDirector управляет процессом создания объекта Coordinates.
 * Использует CoordinatesBuilder для ввода и валидации значений координат X и Y.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class CoordinatesBuilderDirector {
    private InputManager inputManager;
    private CoordinatesBuilder coordinatesBuilder;

    /**
     * Конструктор класса.
     *
     * @param inputManager менеджер ввода, используемый для чтения значений координат.
     */
    public CoordinatesBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        coordinatesBuilder = new CoordinatesBuilder(this.inputManager);
    }

    /**
     * Получает объект Coordinates, вводя и валидируя значения координат X и Y.
     *
     * @return объект Coordinates, если ввод корректен, иначе null.
     * @throws IncorrectInputException если ввод некорректен.
     */
    public Coordinates getCoordinates() throws IncorrectInputException {
        coordinatesBuilder.reset();

        String typeXString = inputManager.toString();
        coordinatesBuilder.inputX();
        if (!typeXString.equals(inputManager.toString())) {
            return null;
        }

        String typeYString = inputManager.toString();
        coordinatesBuilder.inputY();
        if (!typeYString.equals(inputManager.toString())) {
            return null;
        }

        return coordinatesBuilder.getCoordinates();
    }
}