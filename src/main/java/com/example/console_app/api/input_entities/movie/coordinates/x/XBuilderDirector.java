package com.example.console_app.api.input_entities.movie.coordinates.x;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input.classes.input_strategies.KeyboardInput;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

/**
 * Класс XBuilderDirector управляет процессом получения и валидации координаты X.
 * Использует InputManager для чтения ввода и XBuilder для обработки и хранения значения.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class XBuilderDirector {
    private InputManager inputManager;
    private XBuilder coordinateXBuilder;

    /**
     * Конструктор класса.
     *
     * @param inputManager менеджер ввода, используемый для чтения значения координаты X.
     */
    public XBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        coordinateXBuilder = new XBuilder();
    }

    /**
     * Получает значение координаты X от пользователя.
     *
     * @return значение координаты X.
     * @throws IncorrectInputException если ввод некорректен.
     */
    public long getX() throws IncorrectInputException {
        coordinateXBuilder.reset();

        try {
            if (!inputManager.toString().equals("FileInput")) {
                System.out.println("Введите координату x (поле coordinates).");
            }
            coordinateXBuilder.setValue(inputManager.readNext());
            return coordinateXBuilder.getX();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return 404; // Возвращаем значение по умолчанию в случае ошибки
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getX(); // Рекурсивный вызов для повторного ввода
            }
        }
    }
}