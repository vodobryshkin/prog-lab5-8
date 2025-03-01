package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.NoArgsCommand;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 * Класс, реализующий команду вывода всех элементов коллекции в строковом представлении.
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 */
public class ShowCommand implements Command, NoArgsCommand {
    private final CollectionManager collectionManager;
    private final String commandName;
    private final TextManager textManager;

    /**
     * Конструктор класса ShowCommand.
     * @param collectionManager Менеджер коллекции, с которым работает команда.
     */
    public ShowCommand(CollectionManager collectionManager, TextManager textManager) {
        this.collectionManager = collectionManager;
        this.textManager = textManager;
        commandName = "show";
    }

    /**
     * Выполняет команду вывода всех элементов коллекции.
     * @param arg Аргумент команды (не используется, но проверяется на отсутствие).
     * @throws KeyNotFoundException если ключ не найден.
     * @throws IncorrectInputException если введены некорректные данные или передан аргумент.
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        checkIfNoArg(arg, commandName);
        Response response = collectionManager.show();

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Выводит описание команды.
     */
    @Override
    public String describe() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

}
