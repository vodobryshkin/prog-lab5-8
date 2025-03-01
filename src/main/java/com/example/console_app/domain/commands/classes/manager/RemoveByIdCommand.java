package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.IntInputable;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 * Класс RemoveByIdCommand реализует команду для удаления элемента коллекции по его идентификатору (id).
 * Команда принимает один аргумент — целочисленный идентификатор элемента, который необходимо удалить.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class RemoveByIdCommand implements Command, IntInputable {
    private final CollectionManager collectionManager;
    private final TextManager textManager;

    /**
     * Конструктор класса RemoveByIdCommand.
     *
     * @param collectionManager менеджер коллекции, с которым работает команда
     */
    public RemoveByIdCommand(CollectionManager collectionManager, TextManager textManager) {
        this.collectionManager = collectionManager;
        this.textManager = textManager;
    }

    /**
     * Выполняет команду "remove_by_id". Удаляет элемент коллекции по указанному идентификатору.
     *
     * @param arg аргумент команды (идентификатор элемента, который нужно удалить)
     * @throws KeyNotFoundException если элемент с указанным идентификатором не найден
     * @throws IncorrectInputException если введены некорректные данные (например, аргумент не является числом)
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        int id = tryToInt(arg);
        Response response = collectionManager.remove_by_id(id);

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Выводит описание команды "remove_by_id".
     */
    @Override
    public String describe() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}