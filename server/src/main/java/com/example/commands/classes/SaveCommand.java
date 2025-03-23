package com.example.commands.classes;

import com.example.commands.interfaces.NoArgCommand;
import com.example.commands.logic.CollectionManager;
import com.example.repository.exceptions.KeyNotFoundException;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class SaveCommand implements NoArgCommand {
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;

    }
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) throws KeyNotFoundException {
        return collectionManager.save();
    }

    @Override
    public String describe() {
        return "save : сохранить коллекцию в файл";
    }
}
