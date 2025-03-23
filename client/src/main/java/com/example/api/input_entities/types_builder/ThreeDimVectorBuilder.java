package com.example.api.input_entities.types_builder;

import com.example.api.input_entities.exceptions.IncorrectInputException;

public interface ThreeDimVectorBuilder extends VectorBuilder {
    public void inputZ() throws IncorrectInputException, IncorrectInputException;
}
