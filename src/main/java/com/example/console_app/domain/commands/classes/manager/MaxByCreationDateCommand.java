package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.exceptions.EmptyCollectionException;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.NoArgsCommand;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 * Класс MaxByCreationDateCommand реализует команду для вывода объекта коллекции с максимальным значением поля creationDate.
 * Команда не принимает аргументов и выводит объект, у которого значение creationDate является наибольшим.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class MaxByCreationDateCommand implements Command, NoArgsCommand {
    private final CollectionManager collectionManager;
    private final String commandName;
    private final TextManager textManager;

    /**
     * Конструктор класса MaxByCreationDateCommand.
     *
     * @param collectionManager менеджер коллекции, с которым работает команда
     */
    public MaxByCreationDateCommand(CollectionManager collectionManager, TextManager textManager) {
        this.collectionManager = collectionManager;
        this.textManager = textManager;
        commandName = "max_by_creation_date";
    }

    /**
     * Выполняет команду "max_by_creation_date". Проверяет отсутствие аргументов и выводит объект с максимальным значением creationDate.
     *
     * @param arg аргумент команды (должен быть пустым)
     * @throws KeyNotFoundException если ключ не найден
     * @throws IncorrectInputException если введены некорректные данные
     * @throws EmptyCollectionException если коллекция пуста
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException, EmptyCollectionException {
        checkIfNoArg(arg, commandName);
        Response response = collectionManager.max_by_creation_date();

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Выводит описание команды "max_by_creation_date".
     */
    @Override
    public String describe() {
        return "max_by_creation_date : вывести любой объект из коллекции, значение поля creationDate которого является максимальным";
    }
}