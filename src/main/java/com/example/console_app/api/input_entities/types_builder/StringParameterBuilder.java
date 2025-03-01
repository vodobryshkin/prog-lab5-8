package com.example.console_app.api.input_entities.types_builder;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

public interface StringParameterBuilder {
    public void reset();
    public void setString(String valueString) throws IncorrectInputException;
}
