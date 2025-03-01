package com.example.console_app.api.input_entities.movie.operator.eye_color;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.input_entities.types_builder.EnumBuilder;
import com.example.console_app.entities.enums.EyeColor;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Строитель для создания объекта EyeColor на основе строкового представления.
 */
public class EyeColorBuilder implements EnumBuilder {
    EyeColor eyeColor;

    /**
     * Сбрасывает текущее значение EyeColor в null.
     */
    @Override
    public void reset() {
        eyeColor = null;
    }

    /**
     * Устанавливает значение EyeColor на основе строкового представления.
     *
     * @param valueString Строковое представление EyeColor (GREEN, BLACK, BLUE).
     * @throws IncorrectInputException Если valueString не соответствует ни одному из допустимых значений EyeColor.
     */
    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        switch (valueString) {
            case "GREEN":
                eyeColor = EyeColor.GREEN;
                break;
            case "BLACK":
                eyeColor = EyeColor.BLACK;
                break;
            case "BLUE":
                eyeColor = EyeColor.BLUE;
                break;
            default:
                throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\" не соответствует типу EyeColor. ");
        }
    }

    /**
     * Возвращает созданный объект EyeColor.
     *
     * @return Объект EyeColor.
     */
    public EyeColor getEyeColor() {
        return eyeColor;
    }
    
}
