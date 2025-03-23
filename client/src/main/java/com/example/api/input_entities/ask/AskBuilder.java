package com.example.api.input_entities.ask;

import com.example.api.input_entities.exceptions.IncorrectInputException;

/**
 * Класс AskBuilder реализует интерфейс ResponseBuilder и предназначен для обработки и хранения ответа.
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class AskBuilder implements ResponseBuilder {
    private boolean response;

    /**
     * Устанавливает значение ответа на основе входной строки.
     * 
     * @param responseString строка, представляющая ответ. Допустимые значения: "yes" или пустая строка.
     * @throws IncorrectInputException если входная строка не соответствует допустимым значениям.
     */
    @Override
    public void setResponse(String responseString) throws IncorrectInputException {
        if (responseString.equals("") || responseString.equalsIgnoreCase("yes")) {
            response = responseString.equalsIgnoreCase("yes");
        } else {
            throw new IncorrectInputException("Ошибка ввода:" + "\"" + responseString + "\" на вход подаётся \"yes\", или пустая строка. ");
        }
    }

    /**
     * Возвращает текущее значение ответа.
     * 
     * @return true, если ответ "yes", иначе false.
     */
    public boolean getResponse() {
        return response;
    }

    /**
     * Сбрасывает значение ответа на false.
     */
    public void reset() {
        response = false;
    }
    
}