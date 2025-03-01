package com.example.console_app.api.input_entities.movie.operator.location.z;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.input_entities.types_builder.NumberParameterBuilder;

/**
 • Строитель (Builder) для создания значения Z типа long.
 • Реализует интерфейс NumberParameterBuilder.
 */
public class ZBuilder implements NumberParameterBuilder {
    private long z;

    /**
     * Устанавливает значение Z из строки.
     *
     * @param valueString Строковое представление значения Z.
     * @throws IncorrectInputException Если строка не может быть преобразована в long.
     */
    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        try {
            z = Long.parseLong(valueString); 
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\"" + " не соответствует типу long. ");
        }
    }

    /**
     * Сбрасывает значение Z в 0.
     */
    @Override
    public void reset() {
        z = 0;
    }

    /**
     * Возвращает значение Z.
     *
     * @return Значение Z.
     */
    public Long getZ() {
        return z;
    }
    
}
