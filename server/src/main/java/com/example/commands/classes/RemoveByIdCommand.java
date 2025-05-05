package com.example.commands.classes;

import com.example.commands.interfaces.StringArgCommand;
import com.example.commands.logic.CollectionManager;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class RemoveByIdCommand implements StringArgCommand {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie, String login, String password) {
        return collectionManager.removeById(Integer.parseInt(arg));
    }

    @Override
    public String describe() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
