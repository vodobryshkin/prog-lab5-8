package com.example.commands.classes;

import com.example.commands.interfaces.NoArgCommand;
import com.example.commands.logic.CollectionManager;
import com.example.repository.exceptions.KeyNotFoundException;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class MaxByCreationDateCommand implements NoArgCommand {
    private final CollectionManager collectionManager;

    public MaxByCreationDateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie, String login, String password) throws KeyNotFoundException {
        return collectionManager.maxByCreationDate();
    }

    @Override
    public String describe() {
        return "max_by_creation_date : вывести любой объект из коллекции, значение поля creationDate которого является максимальным";
    }
}
