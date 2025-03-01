package com.example.console_app.api.input_entities.exceptions;

/**
 * Исключение IncorrectInputException выбрасывается при некорректном вводе данных.
 * Наследуется от класса Exception.
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class IncorrectInputException extends Exception {
    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param message сообщение, описывающее причину исключения.
     */
    public IncorrectInputException(String message) {
        super(message);
    }
}