package com.example.commands.classes;

import com.example.commands.interfaces.NoArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class RemoveFirstCommand implements NoArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {

        return null;
    }

    @Override
    public String describe() {
        return "remove_first : удалить первый элемент из коллекции";
    }
}
