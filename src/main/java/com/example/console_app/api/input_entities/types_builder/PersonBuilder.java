package com.example.console_app.api.input_entities.types_builder;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

public interface PersonBuilder {
    public void reset();
    public void inputName() throws IncorrectInputException;
    public void inputHeight() throws IncorrectInputException;
    public void inputEyeColor() throws IncorrectInputException;
    public void inputHairColor() throws IncorrectInputException;
    public void inputNationality() throws IncorrectInputException;
    public void inputLocation() throws IncorrectInputException;
}
