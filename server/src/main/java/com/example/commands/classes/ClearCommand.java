package com.example.commands.classes;

import com.example.commands.interfaces.NoArgCommand;
import com.example.commands.logic.CollectionManager;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class ClearCommand implements NoArgCommand {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {
        return collectionManager.clear();
    }

    @Override
    public String describe() {
        return "clear : очистить коллекцию";
    }
}
