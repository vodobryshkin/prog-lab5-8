package com.example.commands.classes;

import com.example.commands.interfaces.NoArgCommand;
import com.example.commands.logic.CollectionManager;
import com.example.repository.exceptions.KeyNotFoundException;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class ShowCommand implements NoArgCommand {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) throws KeyNotFoundException {
        return collectionManager.show();
    }

    @Override
    public String describe() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
