package com.example.console_app.api.input_entities.ask;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input.classes.input_strategies.KeyboardInput;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

/**
 * Класс AskedResponseDirector управляет процессом получения ответа от пользователя.
 * Использует InputManager для чтения ввода и AskBuilder для обработки и хранения ответа.
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class AskedResponseDirector {
    private String message;
    private InputManager inputManager;
    private AskBuilder askBuilder;

    /**
     * Конструктор класса.
     *
     * @param message сообщение, которое будет выведено пользователю.
     * @param inputManager менеджер ввода, используемый для чтения ответа.
     */
    public AskedResponseDirector(String message, InputManager inputManager) {
        this.message = message;
        this.inputManager = inputManager;
        askBuilder = new AskBuilder();
    }

    /**
     * Получает ответ от пользователя.
     *
     * @return true, если ответ "yes", иначе false.
     * @throws IncorrectInputException если ввод некорректен.
     */
    public boolean getResponse() throws IncorrectInputException {
        askBuilder.reset();
        try {
            if (!inputManager.toString().equals("FileInput")) {
                System.out.println(message + "Данное поле необязательно к вводу. Если хотите ввести его, напишите \"yes\", в противном случае нажмите Enter.");
            }
            askBuilder.setResponse(inputManager.readNext());
            return askBuilder.getResponse();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return false;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getResponse();
            }
        }
    }

    /**
     * Устанавливает новое сообщение для пользователя.
     *
     * @param message новое сообщение.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}