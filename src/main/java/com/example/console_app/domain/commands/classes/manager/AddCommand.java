package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.NoArgsCommand;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 * Команда для добавления нового элемента в коллекцию.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class AddCommand implements Command, NoArgsCommand {
    private final CollectionManager receiver;
    private final String commandName;
    private final TextManager textManager;

    /**
     * Конструктор команды.
     *
     * @param receiver объект CollectionManager, который будет выполнять основную логику команды
     */
    public AddCommand(CollectionManager receiver, TextManager textManager) {
        this.receiver = receiver;
        this.textManager = textManager;
        commandName = "add";
    }

    /**
     * Выполняет команду добавления элемента в коллекцию.
     *
     * @param arg аргумент команды (должен быть пустым, так как команда не принимает аргументов)
     * @throws KeyNotFoundException если возникла ошибка при добавлении элемента
     * @throws IncorrectInputException если аргумент команды некорректен
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        checkIfNoArg(arg, commandName);
        Response response = receiver.add();

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Описание команды.
     */
    @Override
    public String describe() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}