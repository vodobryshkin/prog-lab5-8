package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.IntInputable;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

import javax.net.ssl.KeyManager;

/**
 * Класс RemoveAtCommand реализует команду для удаления элемента коллекции по указанному индексу.
 * Команда принимает один аргумент — целочисленный индекс элемента, который необходимо удалить.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class RemoveAtCommand implements Command, IntInputable {
    private final CollectionManager collectionManager;
    private final TextManager textManager;

    /**
     * Конструктор класса RemoveAtCommand.
     *
     * @param collectionManager менеджер коллекции, с которым работает команда
     */
    public RemoveAtCommand(CollectionManager collectionManager, TextManager textManager) {
        this.collectionManager = collectionManager;
        this.textManager = textManager;
    }

    /**
     * Выполняет команду "remove_at". Удаляет элемент коллекции по указанному индексу.
     *
     * @param arg аргумент команды (индекс элемента, который нужно удалить)
     * @throws KeyNotFoundException если элемент с указанным индексом не найден
     * @throws IncorrectInputException если введены некорректные данные (например, аргумент не является числом)
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        int index = tryToInt(arg);
        Response response = collectionManager.remove_at(index);

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Выводит описание команды "remove_at".
     */
    @Override
    public String describe() {
        return "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)";
    }
}