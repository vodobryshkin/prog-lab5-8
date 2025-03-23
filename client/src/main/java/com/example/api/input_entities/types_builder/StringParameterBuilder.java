package com.example.api.input_entities.types_builder;

import com.example.api.input_entities.exceptions.IncorrectInputException;

public interface StringParameterBuilder {
    void reset();
    void setString(String valueString) throws IncorrectInputException, IncorrectInputException;
}
