package com.example.console_app.domain.commands.classes.no_manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.NoArgsCommand;

/**
 * Класс {@class ExitCommand} реализует команду выхода из программы.
 * <p> 
 * Этот класс исполняет команду выхода из программы и описывает её.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class ExitCommand implements Command, NoArgsCommand  {    
    private final String commandName;
    private final TextManager textManager;

    public ExitCommand(TextManager textManager) {
        commandName = "exit";
        this.textManager = textManager;
    }
    /**
     * Исполнение команды с заданным аргументом.
     */
    @Override
    public void execute(String arg) throws IncorrectInputException {
        checkIfNoArg(arg, commandName);

        textManager.print("Работа программы заверещена.");
        System.exit(0);
    }

    /**
     * Описание того, что делает команда
     */
    @Override
    public String describe() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
