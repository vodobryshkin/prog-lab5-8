package com.example.commands.classes;

import com.example.commands.interfaces.AllArgCommand;
import com.example.commands.logic.CollectionManager;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class UpdateCommand implements AllArgCommand {
    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie, String login, String password) {
        return collectionManager.update(Integer.parseInt(arg), movie, login);
    }

    @Override
    public String describe() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
