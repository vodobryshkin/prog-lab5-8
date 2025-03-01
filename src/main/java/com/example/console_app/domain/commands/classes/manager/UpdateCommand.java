package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.IntInputable;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 *  Класс, реализующий команду обновления элемента коллекции, ID которого равен заданному.
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 */
public class UpdateCommand implements Command, IntInputable {
    private final CollectionManager collectionManager;
    private final TextManager textManager;

    /**
     * Конструктор класса UpdateCommand.
     * @param collectionManager Менеджер коллекции, с которым работает команда.
     */
    public UpdateCommand(CollectionManager collectionManager, TextManager textManager) {
        this.collectionManager = collectionManager;
        this.textManager = textManager;
    }

    /**
     * Выполняет команду обновления элемента коллекции по ID.
     * @param arg Аргумент команды - ID элемента для обновления.
     * @throws KeyNotFoundException если элемент с указанным ID не найден.
     * @throws IncorrectInputException если аргумент не является целым числом или введены некорректные данные.
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        int id = tryToInt(arg);
        Response response = collectionManager.update(id);

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Выводит описание команды.
     */
    @Override
    public String describe() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

}
