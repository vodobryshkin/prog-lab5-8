package com.example.api.input_entities.movie.operator.location.y;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.types_builder.NumberParameterBuilder;

/**
 * Строитель (Builder) для создания значения Y типа Double.
 * Реализует интерфейс NumberParameterBuilder.
 */
public class YBuilder implements NumberParameterBuilder {
    private Double y;

    /**
     * Устанавливает значение Y из строки.
     *
     * @param valueString Строковое представление значения Y.
     * @throws IncorrectInputException Если строка не может быть преобразована в Double.
     */
    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        try {
            y = Double.parseDouble(valueString); 
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\"" + " не соответствует типу Double. ");
        }
    }

    /**
     * Сбрасывает значение Y в null.
     */
    @Override
    public void reset() {
        y = null;
    }

    /**
     * Возвращает значение Y.
     *
     * @return Значение Y.
     */
    public Double getY() {
        return y;
    }
}
