package com.example.console_app.domain.commands.exceptions;

/**
 • Исключение, выбрасываемое при обнаружении повтора в стеке команд.
 */
public class StackRepeatException extends Exception {

    /**
     * Создает новое исключение StackRepeatException с заданным сообщением.
     *
     * @param message Сообщение, описывающее причину исключения.
     */
    public StackRepeatException(String message) {
        super(message);
    }
}
