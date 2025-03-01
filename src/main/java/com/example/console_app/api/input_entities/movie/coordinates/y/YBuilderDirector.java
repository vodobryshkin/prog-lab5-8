package com.example.console_app.api.input_entities.movie.coordinates.y;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input.classes.input_strategies.KeyboardInput;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

/**
 * Класс YBuilderDirector управляет процессом получения и валидации координаты Y.
 * Использует InputManager для чтения ввода и YBuilder для обработки и хранения значения.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class YBuilderDirector {
    private InputManager inputManager;
    private YBuilder coordinateYBuilder;

    /**
     * Конструктор класса.
     *
     * @param inputManager менеджер ввода, используемый для чтения значения координаты Y.
     */
    public YBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        coordinateYBuilder = new YBuilder();
    }

    /**
     * Получает значение координаты Y от пользователя.
     *
     * @return значение координаты Y.
     * @throws IncorrectInputException если ввод некорректен.
     */
    public long getY() throws IncorrectInputException {
        coordinateYBuilder.reset();

        try {    
            if (!inputManager.toString().equals("FileInput")) {
                System.out.println("Введите координату y (поле coordinates).");
            }
            coordinateYBuilder.setValue(inputManager.readNext());
            return coordinateYBuilder.getY();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return 404;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getY();
            }
        }
    }   
}