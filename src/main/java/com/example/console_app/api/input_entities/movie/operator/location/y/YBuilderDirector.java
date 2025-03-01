package com.example.console_app.api.input_entities.movie.operator.location.y;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input.classes.input_strategies.KeyboardInput;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

/**
 * Директор для создания объекта Y (координата), используя YBuilder.
 * Отвечает за последовательное построение координаты Y на основе ввода пользователя.
 */
public class YBuilderDirector {
    private InputManager inputManager;
    private YBuilder coordinateYBuilder;
    
    /**
     * Конструктор класса YBuilderDirector.
     *
     * @param inputManager Менеджер ввода, используемый для получения данных от пользователя.
     */
    public YBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        coordinateYBuilder = new YBuilder();
    }

    /**
     * Получает координату Y от пользователя, используя InputManager и YBuilder.
     *
     * @return Значение координаты Y, полученное от пользователя. Возвращает null в случае ошибки при чтении из файла скрипта.
     * @throws IncorrectInputException Если введенное значение координаты Y некорректно.
     */
    public Double getY() throws IncorrectInputException {
        coordinateYBuilder.reset();

        try {    
            if (inputManager.toString() != "FileInput") {
                System.out.println("Введите координату y (поле location).");
            }
            coordinateYBuilder.setValue(inputManager.readNext());
            return coordinateYBuilder.getY();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return null;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getY();
            }
        }
        
    }   
}
