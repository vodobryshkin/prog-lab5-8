package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.NoArgsCommand;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;
/**
 * Класс InfoCommand реализует команду для вывода информации о коллекции.
 * Команда не принимает аргументов и выводит данные о типе коллекции, дате инициализации и количестве элементов.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class InfoCommand implements Command, NoArgsCommand {
    private final CollectionManager collectionManager;
    private final String commandName;
    private final TextManager textManager;

    /**
     * Конструктор класса InfoCommand.
     *
     * @param collectionManager менеджер коллекции, с которым работает команда
     */
    public InfoCommand(CollectionManager collectionManager, TextManager textManager) {
        this.collectionManager = collectionManager;
        this.textManager = textManager;
        commandName = "info";
    }

    /**
     * Выполняет команду "info". Проверяет отсутствие аргументов и выводит информацию о коллекции.
     *
     * @param arg аргумент команды (должен быть пустым)
     * @throws KeyNotFoundException если ключ не найден
     * @throws IncorrectInputException если введены некорректные данные
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        checkIfNoArg(arg, commandName);
        Response response = collectionManager.info();

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Выводит описание команды "info".
     */
    @Override
    public String describe() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}