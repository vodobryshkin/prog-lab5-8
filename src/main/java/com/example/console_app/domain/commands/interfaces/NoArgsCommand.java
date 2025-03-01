package com.example.console_app.domain.commands.interfaces;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

/**
 •  @author Добрышкин Владимир (vodobryshkin)
 •  @version 1.0
 •  @since 2025-22-02
 •  Интерфейс для команд, не принимающих аргументы.
 */
public interface NoArgsCommand {

    /**
     * Проверяет, что команда не была вызвана с аргументом.
     *
     * @param arg Аргумент, переданный команде.  Ожидается "null", если аргумент отсутствует.
     * @param commandName Имя команды, для формирования сообщения об ошибке.
     * @return true, если аргумент равен "null".
     * @throws IncorrectInputException Если аргумент не равен "null", т.е. команда была вызвана с аргументом.
     */
    default boolean checkIfNoArg(String arg, String commandName) throws IncorrectInputException {
        if (!arg.equals("null")) {
            System.out.println(arg);
            throw new IncorrectInputException("Ошибка ввода: " + commandName + " не принимает аргументов. ");
        }
        return true;
    }
}
