package com.example.commands.classes;

import com.example.commands.interfaces.NoArgCommand;
import com.example.commands.logic.CollectionManager;
import com.example.repository.exceptions.KeyNotFoundException;
import domain.chat.classes.ServerAnswerBuffer;
import domain.chat.enums.AnswerStatus;
import entities.classes.Movie;

public class ExitCommand implements NoArgCommand {
    private final CollectionManager collectionManager;

    public ExitCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) throws KeyNotFoundException {
        collectionManager.save();
        return new ServerAnswerBuffer("exit", AnswerStatus.EXIT, null);
    }

    @Override
    public String describe() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
