package com.example.commands.classes;

import com.example.commands.interfaces.NoArgCommand;
import com.example.commands.logic.CollectionManager;
import com.example.repository.exceptions.KeyNotFoundException;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class PrintUniqueMpaaRating implements NoArgCommand {
    private final CollectionManager collectionManager;

    public PrintUniqueMpaaRating(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie, String login, String password) throws KeyNotFoundException {
        return collectionManager.printUniqueMpaaRating();
    }

    @Override
    public String describe() {
        return "print_unique_mpaa_rating : вывести уникальные значения поля mpaaRating всех элементов в коллекции";
    }
}
