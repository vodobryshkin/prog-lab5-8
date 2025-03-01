package com.example.console_app.domain.commands.classes.no_manager;

import java.util.LinkedHashMap;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.NoArgsCommand;

/**
 * Класс {@class HelpCommand} реализует команду справки.
 * <p> 
 * Этот класс исполняет команду справки и описывает её.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class HelpCommand implements Command, NoArgsCommand {
    private final LinkedHashMap<String, Command> commands;
    private final String commandName;
    private final TextManager textManager;

    /**
     * Конструктор класса {@class HelpCommand}.
     */
    public HelpCommand(LinkedHashMap<String, Command> commands, TextManager textManager) {
        this.commands = commands;
        this.textManager = textManager;
        commandName = "help";
    }

    /**
     * Исполнение команды.
     */
    @Override
    public void execute(String arg) throws IncorrectInputException {
        StringBuilder message = new StringBuilder();

        checkIfNoArg(arg, commandName);
        for (Command value : commands.values()) {
            message.append(value.describe()).append("\n");
        }

        textManager.print(message.toString());
    }

    /**
     * Описание того, что делает команда
     */
    @Override
    public String describe() {
        return "help : вывести справку по доступным командам";
    }
}
