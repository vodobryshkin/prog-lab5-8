package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 * Команда для фильтрации элементов коллекции, у которых поле name начинается с заданной подстроки.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-24-02
 */
public class FilterStartsWithNameCommand implements Command {
    private final CollectionManager collectionManager;
    private final TextManager textManager;

    /**
     * Конструктор команды.
     *
     * @param collectionManager менеджер коллекции, с которым будет работать команда.
     */
    public FilterStartsWithNameCommand(CollectionManager collectionManager, TextManager textManager) {
        this.collectionManager = collectionManager;
        this.textManager = textManager;
    }

    /**
     * Выполняет команду фильтрации элементов коллекции по началу строки в поле name.
     *
     * @param arg подстрока, с которой должно начинаться поле name.
     * @throws KeyNotFoundException если элемент с указанным ключом не найден.
     * @throws IncorrectInputException если ввод некорректен.
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        Response response = collectionManager.filter_starts_with_name(arg);

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Выводит описание команды.
     */
    @Override
    public String describe() {
        return "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}