package com.example.console_app.api.input_entities.types_builder;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

public interface ThreeDimVectorBuilder extends VectorBuilder {
    public void inputZ() throws IncorrectInputException;
}
