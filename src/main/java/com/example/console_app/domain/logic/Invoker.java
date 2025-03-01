package com.example.console_app.domain.logic;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.client.CommandManager;
import com.example.console_app.domain.commands.exceptions.EmptyCollectionException;
import com.example.console_app.domain.commands.exceptions.StackRepeatException;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 * Класс Invoker перенаправляет команды на выполнение.
 * <p>
 * Этот класс исполняет переданную в него команду.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class Invoker {
    private final CommandManager commands;

    /**
     * Конструктор класса Invoker.
     */
    public Invoker(CommandManager commands) {
        this.commands = commands;
    }

    public void commandExecute(String[] arg) throws KeyNotFoundException, IncorrectInputException, EmptyCollectionException, StackRepeatException {
        commands.get(arg[0]).execute(arg[1]);
    }
}
