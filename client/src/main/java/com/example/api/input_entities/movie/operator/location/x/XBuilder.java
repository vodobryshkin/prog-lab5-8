package com.example.api.input_entities.movie.operator.location.x;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.types_builder.NumberParameterBuilder;
import com.example.api.input_entities.types_builder.Validatable;

/**
 * Строитель (Builder) для создания значения X типа Long.
 * Реализует интерфейсы NumberParameterBuilder и Validatable.
 */
public class XBuilder implements NumberParameterBuilder, Validatable {
    private Long x;

    /**
     * Проверяет, было ли установлено значение X.
     *
     * @return true, если значение X не равно null, иначе false.
     */
    @Override
    public boolean check() {
        return x != null;
    }

    /**
     * Устанавливает значение X из строки.
     *
     * @param valueString Строковое представление значения X.
     * @throws IncorrectInputException Если строка не может быть преобразована в Long или значение X равно null.
     */
    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        try {
            x = Long.parseLong(valueString); 
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\"" + " не соответствует типу Long. ");
        }

        if (!check()) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\"" + " поле не может быть равно null. ");
        }
    }

    /**
     * Сбрасывает значение X в null.
     */
    @Override
    public void reset() {
        x = null;
    }

    /**
     * Возвращает значение X.
     *
     * @return Значение X.
     */
    public Long getX() {
        return x;
    }
    
}
