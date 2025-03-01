package com.example.console_app.domain.commands.classes.manager;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.commands.interfaces.NoArgsCommand;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 * Класс PrintUniqueMpaaRatingCommand реализует команду для вывода уникальных значений поля mpaaRating всех элементов коллекции.
 * Команда не принимает аргументов и выводит список уникальных значений поля mpaaRating.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class PrintUniqueMpaaRatingCommand implements Command, NoArgsCommand {
    private final CollectionManager collectionManager;
    private final String commandName;
    private final TextManager textManager;

    /**
     * Конструктор класса PrintUniqueMpaaRatingCommand.
     *
     * @param collectionManager менеджер коллекции, с которым работает команда
     */
    public PrintUniqueMpaaRatingCommand(CollectionManager collectionManager, TextManager textManager) {
        this.collectionManager = collectionManager;
        this.textManager = textManager;
        commandName = "print_unique_mpaa_rating";
    }

    /**
     * Выполняет команду "print_unique_mpaa_rating". Проверяет отсутствие аргументов и выводит уникальные значения поля mpaaRating.
     *
     * @param arg аргумент команды (должен быть пустым)
     * @throws KeyNotFoundException если ключ не найден
     * @throws IncorrectInputException если введены некорректные данные
     */
    @Override
    public void execute(String arg) throws KeyNotFoundException, IncorrectInputException {
        checkIfNoArg(arg, commandName);
        Response response = collectionManager.print_unique_mpaa_rating();

        if (response.to_input()) {
            textManager.print(response.message());
        }
    }

    /**
     * Выводит описание команды "print_unique_mpaa_rating".
     */
    @Override
    public String describe() {
        return "print_unique_mpaa_rating : вывести уникальные значения поля mpaaRating всех элементов в коллекции";
    }
}