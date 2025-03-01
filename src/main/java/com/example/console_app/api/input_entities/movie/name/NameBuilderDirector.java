package com.example.console_app.api.input_entities.movie.name;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input.classes.input_strategies.KeyboardInput;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

/**
 * Класс NameBuilderDirector управляет процессом получения имени через 
 * NameBuilder и InputManager. Он обрабатывает ввод имени и управляет 
 * исключениями, возникающими при некорректном вводе.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class NameBuilderDirector {
    private InputManager inputManager;
    private NameBuilder nameBuilder;

    /**
     * Конструктор класса NameBuilderDirector.
     *
     * @param inputManager объект InputManager для обработки ввода.
     */
    public NameBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        nameBuilder = new NameBuilder();
    }

    /**
     * Получает имя от пользователя, используя NameBuilder.
     * Если ввод некорректен, выводит сообщение об ошибке и запрашивает ввод повторно.
     *
     * @return введенное имя, или null, если ввод был из файла и произошла ошибка.
     * @throws IncorrectInputException если возникла ошибка при обработке ввода.
     */
    public String getName() throws IncorrectInputException {
        nameBuilder.reset();

        try {
            if (!inputManager.toString().equals("FileInput")) {
                System.out.println("Введите поле name.");
            }
            nameBuilder.setString(inputManager.readNext());
            return nameBuilder.getName();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + " Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());
                return null;
            } else {
                System.out.println(e.getMessage() + " Повторите попытку ввода.");
                return getName();
            }
        }
    }
}
