package com.example.api.input_entities.movie.oscars_count;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.types_builder.NumberParameterBuilder;
import com.example.api.input_entities.types_builder.Validatable;

/**
 * Класс OscarsCountBuilder реализует интерфейсы NumberParameterBuilder и Validatable,
 * обеспечивая создание и валидацию количества Оскаров.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class OscarsCountBuilder implements NumberParameterBuilder, Validatable {
    private Long x;

    /**
     * Проверяет, является ли текущее значение количества Оскаров корректным.
     *
     * @return true, если значение больше 0 и не равно null; иначе false.
     */
    @Override
    public boolean check() {
        return x != null && x > 0;
    }

    /**
     * Устанавливает значение количества Оскаров из строки.
     *
     * @param valueString строковое представление количества Оскаров.
     * @throws IncorrectInputException если введенное значение не может быть преобразовано в Long
     *                                  или не соответствует требованиям (должно быть больше 0).
     */
    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        
        try {
            x = Long.parseLong(valueString); 
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\"" + " не соответствует типу Long. ");
        }

        if (!check()) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\"" + " поле должно быть больше 0 и  не может быть равно null. ");
        }
    }

    /**
     * Сбрасывает текущее значение количества Оскаров на null.
     */
    @Override
    public void reset() {
        x = null;
    }

    /**
     * Получает текущее значение количества Оскаров.
     *
     * @return текущее значение количества Оскаров или null, если оно не установлено.
     */
    public Long getOscarsCount() {
        return x;
    }
    
}
