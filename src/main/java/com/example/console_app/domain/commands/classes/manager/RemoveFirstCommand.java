package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.exceptions.EmptyCollectionException;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 *  <p>
 *  Класс, реализующий команду удаления первого элемента из коллекции.
 *  </p>
 */
public class RemoveFirstCommand implements Command {
    private final CollectionManager collectionManager;
    private final TextManager textManager;

    /**
     * Конструктор класса RemoveFirstCommand.
     * @param collectionManager Менеджер коллекции, с которым работает команда.
     */
    public RemoveFirstCommand(CollectionManager collectionManager, TextManager textManager) {
        this.collectionManager = collectionManager;
        this.textManager = textManager;
    }

    /**
     * Выполняет команду удаления первого элемента из коллекции.
     * @param arg Аргумент команды (не используется).
     * @throws KeyNotFoundException если ключ не найден (не применимо, но проброшено из CollectionManager).
     * @throws IncorrectInputException если введены некорректные данные (не применимо, но проброшено из CollectionManager).
     * @throws EmptyCollectionException если коллекция пуста.
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException, EmptyCollectionException {
        Response response = collectionManager.remove_first();

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Выводит описание команды.
     */
    @Override
    public String describe() {
        return "remove_first : удалить первый элемент из коллекции";
    }
}
