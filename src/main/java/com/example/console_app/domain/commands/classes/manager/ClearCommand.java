package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.NoArgsCommand;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 * Команда для очистки коллекции.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-24-02
 */
public class ClearCommand implements Command, NoArgsCommand {
    private final CollectionManager collectionManager;
    private final String commandName;
    private final TextManager textManager;

    /**
     * Конструктор команды.
     *
     * @param collectionManager объект CollectionManager, который будет выполнять основную логику команды
     */
    public ClearCommand(CollectionManager collectionManager, TextManager textManager) {
        this.collectionManager = collectionManager;
        this.textManager = textManager;
        commandName = "clear";
    }

    /**
     * Выполняет команду очистки коллекции.
     *
     * @param arg аргумент команды (должен быть пустым, так как команда не принимает аргументов)
     * @throws KeyNotFoundException если возникла ошибка при очистке коллекции
     * @throws IncorrectInputException если аргумент команды некорректен
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        checkIfNoArg(arg, commandName);
        Response response = collectionManager.clear();

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Описание команды.
     */
    @Override
    public String describe() {
        return "clear : очистить коллекцию";
    }
}