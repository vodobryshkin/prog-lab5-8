package com.example.commands.logic;

import com.example.repository.exceptions.KeyNotFoundException;
import com.example.repository.interfaces.Repository;
import domain.chat.classes.ServerAnswerBuffer;
import domain.chat.enums.AnswerStatus;
import entities.classes.Movie;

public class CollectionManager {
    private final Repository repository;

    public CollectionManager(Repository repository) {
        this.repository = repository;
    }

    public ServerAnswerBuffer add(String commandName, Movie movie) {
        repository.add(movie);
        return new ServerAnswerBuffer(commandName, AnswerStatus.OK, "Элемент был добавлен в коллекцию.");
    }

    public ServerAnswerBuffer save() throws KeyNotFoundException {
        repository.writeIntoFile();
        return new ServerAnswerBuffer(null, AnswerStatus.OK, null);
    }
}
