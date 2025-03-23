package com.example.api.input_entities.movie.coordinates.x;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.types_builder.NumberParameterBuilder;
import com.example.api.input_entities.types_builder.Validatable;

/**
 * Класс XBuilder реализует интерфейсы NumberParameterBuilder и Validatable.
 * Предназначен для обработки и валидации значения координаты X.
 * Значение должно быть типа long и не превышать 611.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class XBuilder implements NumberParameterBuilder, Validatable {
    private long x;

    /**
     * Проверяет, что значение координаты X не превышает 611.
     *
     * @return true, если значение допустимо, иначе false.
     */
    @Override
    public boolean check() {
        return x <= 611;
    }

    /**
     * Устанавливает значение координаты X на основе строки.
     *
     * @param valueString строка, представляющая значение координаты X.
     * @throws IncorrectInputException если строка не может быть преобразована в long или значение превышает 611.
     */
    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        try {
            x = Long.parseLong(valueString); 
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\"" + " не соответствует типу long. ");
        }

        if (!check()) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\"" + " максимальное значение поля равно 611. ");
        }
    }

    /**
     * Сбрасывает значение координаты X на 0.
     */
    @Override
    public void reset() {
        x = 0;
    }

    /**
     * Возвращает текущее значение координаты X.
     *
     * @return значение координаты X.
     */
    public long getX() {
        return x;
    }
}