package com.example.api.input_entities.types_builder;

import com.example.api.input_entities.exceptions.IncorrectInputException;

/**
 * Интерфейс {@interface Builder} определяет шаблон для Builder-ов с возвращаемым типом String.
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public interface VectorBuilder {
    public void reset();

    public void inputX() throws IncorrectInputException;
    public void inputY() throws IncorrectInputException;
}
