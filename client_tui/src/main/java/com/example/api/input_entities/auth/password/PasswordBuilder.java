package com.example.api.input_entities.auth.password;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.types_builder.StringParameterBuilder;

public class PasswordBuilder  implements StringParameterBuilder {
    private String name;

    @Override
    public void reset() {
        name = "";
    }

    @Override
    public void setString(String valueString) throws IncorrectInputException {
        try {
            name = valueString;
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\" не соответствует типу String. ");
        }
    }

    public String getPassword() {
        return name;
    }
}
