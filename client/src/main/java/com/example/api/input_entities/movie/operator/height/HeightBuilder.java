package com.example.api.input_entities.movie.operator.height;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.types_builder.NumberParameterBuilder;
import com.example.api.input_entities.types_builder.Validatable;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Строитель для создания объекта Height (рост) на основе строкового представления.
 */
public class HeightBuilder implements NumberParameterBuilder, Validatable {
    private int height;

    /**
     * Устанавливает значение Height на основе строкового представления.
     *
     * @param valueString Строковое представление Height (целое число).
     * @throws IncorrectInputException Если valueString не может быть преобразовано в целое число или если Height не больше 0.
     */
    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        try {
            height = Integer.parseInt(valueString); 
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\"" + " не соответствует типу int. ");
        }

        if (!check()) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\"" + " должно быть больше 0. ");
        }
    }

    /**
     * Сбрасывает текущее значение Height в 0.
     */
    @Override
    public void reset() {
        height = 0;
    }

    /**
     * Возвращает созданный объект Height.
     *
     * @return Объект Height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Проверяет, что значение Height больше 0.
     *
     * @return true, если Height больше 0, false в противном случае.
     */
    @Override
    public boolean check() {
        return height > 0;
    }
    
}
