package com.example.console_app.api.input_entities.movie.coordinates;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.input_entities.movie.coordinates.x.XBuilderDirector;
import com.example.console_app.api.input_entities.movie.coordinates.y.YBuilderDirector;
import com.example.console_app.api.input_entities.types_builder.VectorBuilder;
import com.example.console_app.entities.classes.Coordinates;

/**
 * Класс CoordinatesBuilder реализует интерфейс VectorBuilder и предназначен для создания и управления объектом Coordinates.
 * Использует XBuilderDirector и YBuilderDirector для ввода и валидации значений координат X и Y.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class CoordinatesBuilder implements VectorBuilder {
    private Coordinates coordinates;
    private InputManager inputManager;
    private XBuilderDirector xBuilderDirector;
    private YBuilderDirector yBuilderDirector;

    /**
     * Конструктор класса.
     *
     * @param inputManager менеджер ввода, используемый для чтения значений координат.
     */
    public CoordinatesBuilder(InputManager inputManager) {
        this.inputManager = inputManager;

        coordinates = new Coordinates();
        xBuilderDirector = new XBuilderDirector(this.inputManager);
        yBuilderDirector = new YBuilderDirector(this.inputManager);
    }

    /**
     * Сбрасывает объект Coordinates, создавая новый экземпляр.
     */
    @Override
    public void reset() {
        coordinates = new Coordinates();
    }

    /**
     * Вводит значение координаты X с помощью XBuilderDirector и устанавливает его в объект Coordinates.
     *
     * @throws IncorrectInputException если ввод некорректен.
     */
    @Override
    public void inputX() throws IncorrectInputException {
        coordinates.setX(xBuilderDirector.getX());
    }

    /**
     * Вводит значение координаты Y с помощью YBuilderDirector и устанавливает его в объект Coordinates.
     *
     * @throws IncorrectInputException если ввод некорректен.
     */
    @Override
    public void inputY() throws IncorrectInputException {
        coordinates.setY(yBuilderDirector.getY());
    }

    /**
     * Возвращает текущий объект Coordinates.
     *
     * @return объект Coordinates.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
}