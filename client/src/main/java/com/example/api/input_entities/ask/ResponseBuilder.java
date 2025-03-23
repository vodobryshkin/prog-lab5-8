package com.example.api.input_entities.ask;

import com.example.api.input_entities.exceptions.IncorrectInputException;

/**
 * Интерфейс ResponseBuilder определяет контракт для классов, которые обрабатывают и устанавливают ответы.
 * Содержит константы для допустимых значений ответа.
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public interface ResponseBuilder {
    /** Строка, представляющая положительный ответ ("да"). */
    String yesString = "да";

    /** Строка, представляющая отрицательный ответ (пустая строка). */
    String noString = "";

    /**
     * Устанавливает ответ на основе входной строки.
     *
     * @param responseString строка, представляющая ответ.
     * @throws IncorrectInputException если входная строка не соответствует допустимым значениям.
     */
    void setResponse(String responseString) throws IncorrectInputException, IncorrectInputException;
}