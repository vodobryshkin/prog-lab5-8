package com.example.console_app.domain.commands.interfaces;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.domain.commands.exceptions.EmptyCollectionException;
import com.example.console_app.domain.commands.exceptions.StackRepeatException;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 * Интерфейс Command создаёт шаблон для реализации команд, как классов.
 * <p> 
 * Этот интерфейс вводит описание того, как исполнять команду и описывать её.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public interface Command {
    /**
     * Исполнение команды с заданным аргументом.
     */
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException, EmptyCollectionException, StackRepeatException;


    /**
     * Описание того, что делает команда.
     */
    public String describe();
}
