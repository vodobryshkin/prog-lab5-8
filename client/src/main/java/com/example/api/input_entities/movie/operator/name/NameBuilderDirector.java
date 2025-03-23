package com.example.api.input_entities.movie.operator.name;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input.classes.input_strategies.KeyboardInput;
import com.example.api.input_entities.exceptions.IncorrectInputException;

/**
 * Директор для создания имени (name), используя NameBuilder.
 * Отвечает за последовательное построение имени на основе ввода пользователя.
 */
public class NameBuilderDirector {
    private InputManager inputManager;
    private NameBuilder nameBuilder;

    /**
     * Конструктор класса NameBuilderDirector.
     *
     * @param inputManager Менеджер ввода, используемый для получения данных от пользователя.
     */
    public NameBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        nameBuilder = new NameBuilder();
    }
    
    /**
     * Получает имя от пользователя, используя InputManager и NameBuilder.
     *
     * @return Имя, полученное от пользователя. Возвращает null в случае ошибки при чтении из файла скрипта.
     * @throws IncorrectInputException Если введенное имя некорректно.
     */
    public String getName() throws IncorrectInputException {
        nameBuilder.reset();

        try {
            if (inputManager.toString() != "FileInput") {
                System.out.println("Введите поле name (поле operator).");
            }
            nameBuilder.setString(inputManager.readNext());
            return nameBuilder.getName();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return null;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getName();
            }
        }
    }
}
