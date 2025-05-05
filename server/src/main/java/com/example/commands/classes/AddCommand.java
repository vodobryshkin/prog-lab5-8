package com.example.commands.classes;

import com.example.commands.interfaces.MovieArgCommand;
import com.example.commands.logic.CollectionManager;
import com.example.repository.exceptions.KeyNotFoundException;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class AddCommand implements MovieArgCommand {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie, String login, String password) throws KeyNotFoundException {
        String name = "add";
        return collectionManager.add(name, movie);
    }

    @Override
    public String describe() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
