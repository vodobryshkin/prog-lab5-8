package com.example.console_app.api.input_entities.types_builder;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

public interface NumberParameterBuilder {
    public void reset();
    public void setValue(String valueString) throws IncorrectInputException;
}
