package com.example.console_app.api.input_entities.movie.operator.location;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.entities.classes.Location;

/**
 * Директор для создания объекта Location, используя LocationBuilder.
 * Отвечает за последовательное построение Location (координат x, y, z) на основе ввода пользователя.
 */
public class LocationBuilderDirector {
    private InputManager inputManager;
    private LocationBuilder locationBuilder;
    
    /**
     * Конструктор класса LocationBuilderDirector.
     *
     * @param inputManager Менеджер ввода, используемый для получения данных от пользователя.
     */
    public LocationBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        locationBuilder = new LocationBuilder(this.inputManager);
    }
    
    /**
     * Получает Location от пользователя, используя InputManager и LocationBuilder.
     *
     * @return Location, полученный от пользователя, или null, если во время ввода произошла смена стратегии ввода (например, при ошибке в файле скрипта).
     * @throws IncorrectInputException Если введенные значения координат x, y или z некорректны.
     */
    public Location getLocation() throws IncorrectInputException {
        locationBuilder.reset();

        String typeXString = inputManager.toString();
        locationBuilder.inputX();
        if (!typeXString.equals(inputManager.toString())) {
            return null;
        }

        String typeYString = inputManager.toString();
        locationBuilder.inputY();
        if (!typeYString.equals(inputManager.toString())) {
            return null;
        }

        String typeZString = inputManager.toString();
        locationBuilder.inputZ();
        if (!typeZString.equals(inputManager.toString())) {
            return null;
        }

        return locationBuilder.getLocation();
    }
}
