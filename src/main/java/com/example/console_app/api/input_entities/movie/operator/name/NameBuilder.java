package com.example.console_app.api.input_entities.movie.operator.name;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.input_entities.types_builder.StringParameterBuilder;
import com.example.console_app.api.input_entities.types_builder.Validatable;

/**
 * Строитель (Builder) для создания имени (name) типа String.
 * Реализует интерфейсы StringParameterBuilder и Validatable.
 */
public class NameBuilder implements StringParameterBuilder, Validatable {
    private String name;

    /**
     * Проверяет, является ли имя валидным (не null и не пустой строкой).
     *
     * @return true, если имя не равно null и не является пустой строкой, иначе false.
     */
    @Override
    public boolean check() {
        return name != null && !name.isEmpty();
    }

    /**
     * Сбрасывает имя к пустой строке.
     */
    @Override
    public void reset() {
        name = "";
    }

    /**
     * Устанавливает имя из строки.
     *
     * @param valueString Строковое представление имени.
     * @throws IncorrectInputException Если строка null или возникла ошибка при установке значения.  (В данном случае исключение NumberFormatException не должно возникнуть, но указано в catch для соответствия исходному коду).
     */
    @Override
    public void setString(String valueString) throws IncorrectInputException {
        try {
            name = valueString;
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\" не соответствует типу String. ");
        }

        if (!check()) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\" не может быть равно null или \"\". ");
        }
    }

    /**
     * Возвращает имя.
     *
     * @return Имя.
     */
    public String getName() {
        return name;
    }
    
}
