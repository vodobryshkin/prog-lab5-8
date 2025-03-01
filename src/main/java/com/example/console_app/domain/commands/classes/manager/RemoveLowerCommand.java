package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.NoArgsCommand;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 *  Класс, реализующий команду удаления из коллекции всех элементов, меньших, чем заданный.
 */
public class RemoveLowerCommand implements Command, NoArgsCommand {
    private final CollectionManager collectionManager;
    private final String commandName;
    private final TextManager textManager;

    /**
     * Конструктор класса RemoveLowerCommand.
     * @param collectionManager Менеджер коллекции, с которым работает команда.
     */
    public RemoveLowerCommand(CollectionManager collectionManager, TextManager textManager) {
        this.collectionManager = collectionManager;
        this.textManager = textManager;
        commandName = "remove_lower";
    }

    /**
     * Выполняет команду удаления элементов, меньших, чем заданный.
     * @param arg Аргумент команды (не используется, но проверяется на отсутствие).
     * @throws KeyNotFoundException если ключ не найден.
     * @throws IncorrectInputException если введены некорректные данные или передан аргумент.
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        checkIfNoArg(arg, commandName);
        Response response = collectionManager.remove_lower();

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Выводит описание команды.
     */
    @Override
    public String describe() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

}
