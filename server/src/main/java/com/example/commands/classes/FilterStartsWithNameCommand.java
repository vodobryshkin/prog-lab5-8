package com.example.commands.classes;

import com.example.commands.interfaces.StringArgCommand;
import com.example.commands.logic.CollectionManager;
import com.example.repository.exceptions.KeyNotFoundException;
import domain.chat.classes.ServerAnswerBuffer;
import domain.command.classes.CommandManager;
import entities.classes.Movie;

public class FilterStartsWithNameCommand implements StringArgCommand {
    private final CollectionManager collectionManager;

    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie, String login, String password) throws KeyNotFoundException {
        return collectionManager.filterStartsWithName(arg);
    }

    @Override
    public String describe() {
        return "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}
