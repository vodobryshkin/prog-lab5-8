package com.example.api.input_entities.types_builder;

import com.example.api.input_entities.exceptions.IncorrectInputException;

public interface EnumBuilder {
    void reset();
    void setValue(String valueString) throws IncorrectInputException, IncorrectInputException;
}
