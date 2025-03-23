package com.example.api.input_entities.movie.operator.height;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input.classes.input_strategies.KeyboardInput;
import com.example.api.input_entities.exceptions.IncorrectInputException;

/**
 * Директор для создания объекта height, используя HeightBuilder.
 * Отвечает за последовательное построение height на основе ввода пользователя.
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class HeightBuilderDirector {
    private InputManager inputManager;
    private HeightBuilder heightBuilder;
    
    /**
     * Конструктор класса HeightBuilderDirector.
     *
     * @param inputManager Менеджер ввода, используемый для получения данных от пользователя.
     */
    public HeightBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        heightBuilder = new HeightBuilder();
    }

    /**
     * Получает height от пользователя, используя InputManager и HeightBuilder.
     *
     * @return Значение height, полученное от пользователя. Возвращает 404 в случае ошибки при чтении из файла скрипта.
     * @throws IncorrectInputException Если введенное значение height некорректно.
     */
    public int getHeight() throws IncorrectInputException {
        heightBuilder.reset();

        try {
            if (inputManager.toString() != "FileInput") {
                System.out.println("Введите поле height (поле operator).");
            }
            heightBuilder.setValue(inputManager.readNext());
            return heightBuilder.getHeight();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return 404;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getHeight();
            }
        }
        
    }
}
