package com.example.api.input_entities.auth.login;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.types_builder.StringParameterBuilder;
import com.example.api.input_entities.types_builder.Validatable;

public class LoginBuilder implements StringParameterBuilder, Validatable {
        private String name;

        @Override
        public boolean check() {
            return !name.isEmpty();
        }

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

            if (!check()) {
                throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\" не может быть равно null или \"\". ");
            }
        }

        public String getLogin() {
            return name;
        }
}
