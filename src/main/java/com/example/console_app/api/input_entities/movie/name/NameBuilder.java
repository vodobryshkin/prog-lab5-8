package com.example.console_app.api.input_entities.movie.name;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.input_entities.types_builder.StringParameterBuilder;
import com.example.console_app.api.input_entities.types_builder.Validatable;

/**
 * Класс NameBuilder отвечает за создание и валидацию имени.
 * Реализует интерфейсы StringParameterBuilder и Validatable.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class NameBuilder implements StringParameterBuilder, Validatable {
    private String name;

    /**
     * Проверяет, является ли имя допустимым (не null и не пустая строка).
     *
     * @return true, если имя корректно; false в противном случае.
     */
    @Override
    public boolean check() {
        return name != "" && name != null;
    }

    /**
     * Сбрасывает значение имени на пустую строку.
     */
    @Override
    public void reset() {
        name = "";
    }

    /**
     * Устанавливает значение имени и проверяет его на корректность.
     *
     * @param valueString значение, которое нужно установить в качестве имени.
     * @throws IncorrectInputException если значение некорректно или не соответствует типу String.
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
     * Получает текущее значение имени.
     *
     * @return текущее имя.
     */
    public String getName() {
        return name;
    }
    
}
