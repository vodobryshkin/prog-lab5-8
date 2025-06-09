package com.example.api.input_entities.types_builder;

import com.example.api.input_entities.exceptions.IncorrectInputException;

public interface MovieBuilder {
    void reset();
    void inputName() throws IncorrectInputException;
    void inputCoordinates() throws IncorrectInputException;
    void inputOscarsCount() throws IncorrectInputException;
    void inputGenre() throws IncorrectInputException;
    void inputMpaaRating() throws IncorrectInputException;
    void inputOperator() throws IncorrectInputException, IncorrectInputException;
}
