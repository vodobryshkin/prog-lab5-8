package com.example.commands.classes;

import com.example.commands.interfaces.StringArgCommand;
import com.example.commands.logic.CollectionManager;
import com.example.repository.exceptions.KeyNotFoundException;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class RemoveAtIndexCommand implements StringArgCommand {
    private final CollectionManager collectionManager;

    public RemoveAtIndexCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie, String login, String password) throws KeyNotFoundException {
        return collectionManager.removeAt(Integer.parseInt(arg));
    }

    @Override
    public String describe() {
        return "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)";
    }
}
