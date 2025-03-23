/**
 * Исключение, выбрасываемое при отсутствии ключа.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
package com.example.repository.exceptions;

public class KeyNotFoundException extends Exception {
    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param message сообщение, описывающее причину исключения
     */
    public KeyNotFoundException(String message) {
        super(message);
    }
}