package com.example.api.input_entities.types_builder;

import com.example.api.input_entities.exceptions.IncorrectInputException;

public interface PersonBuilder {
    void reset();
    void inputName() throws IncorrectInputException, IncorrectInputException;
    void inputHeight() throws IncorrectInputException;
    void inputEyeColor() throws IncorrectInputException;
    void inputHairColor() throws IncorrectInputException;
    void inputNationality() throws IncorrectInputException;
    void inputLocation() throws IncorrectInputException;
}
