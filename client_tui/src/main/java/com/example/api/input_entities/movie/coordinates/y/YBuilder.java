package com.example.api.input_entities.movie.coordinates.y;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.types_builder.NumberParameterBuilder;

/**
 * Класс YBuilder реализует интерфейс NumberParameterBuilder и предназначен для обработки и хранения значения координаты Y.
 * Значение должно быть типа long.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class YBuilder implements NumberParameterBuilder {
    private long y;

    /**
     * Устанавливает значение координаты Y на основе строки.
     *
     * @param valueString строка, представляющая значение координаты Y.
     * @throws IncorrectInputException если строка не может быть преобразована в long.
     */
    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        try {
            y = Long.parseLong(valueString); 
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\"" + " не соответствует типу long. ");
        }
    }

    /**
     * Сбрасывает значение координаты Y на 0.
     */
    @Override
    public void reset() {
        y = 0;
    }

    /**
     * Возвращает текущее значение координаты Y.
     *
     * @return значение координаты Y.
     */
    public long getY() {
        return y;
    }
}