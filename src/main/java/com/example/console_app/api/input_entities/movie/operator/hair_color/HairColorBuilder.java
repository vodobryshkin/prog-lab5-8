package com.example.console_app.api.input_entities.movie.operator.hair_color;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.input_entities.types_builder.EnumBuilder;
import com.example.console_app.entities.enums.HairColor;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Строитель для создания объекта HairColor на основе строкового представления.
 */
public class HairColorBuilder implements EnumBuilder {
    HairColor hairColor;

    /**
     * Сбрасывает текущее значение HairColor в null.
     */
    @Override
    public void reset() {
        hairColor = null;
    }

    /**
     * Устанавливает значение HairColor на основе строкового представления.
     *
     * @param valueString Строковое представление HairColor (GREEN, RED, ORANGE, WHITE, BROWN).
     * @throws IncorrectInputException Если valueString не соответствует ни одному из допустимых значений HairColor.
     */
    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        switch (valueString) {
            case "GREEN":
                hairColor = HairColor.GREEN;
                break;
            case "RED":
                hairColor = HairColor.RED;
                break;
            case "ORANGE":
                hairColor = HairColor.ORANGE;
                break;
            case "WHITE":
                hairColor = HairColor.WHITE;
                break;
            case "BROWN":
                hairColor = HairColor.BROWN;
                break;
            default:
                throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\" не соответствует типу HairColor. ");
        }
    }

    /**
     * Возвращает созданный объект HairColor.
     *
     * @return Объект HairColor.
     */
    public HairColor getHairColor() {
        return hairColor;
    }
    
}
