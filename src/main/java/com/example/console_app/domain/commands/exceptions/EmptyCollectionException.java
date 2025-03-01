package com.example.console_app.domain.commands.exceptions;

/**
 • Исключение, выбрасываемое при попытке выполнить операцию над пустой коллекцией.
 */
public class EmptyCollectionException extends Exception {

    /**
     * Создает новое исключение EmptyCollectionException с заданным сообщением.
     *
     * @param message Сообщение, описывающее причину исключения (коллекция пуста).
     */
    public EmptyCollectionException(String message) {
        super(message);
    }
}
