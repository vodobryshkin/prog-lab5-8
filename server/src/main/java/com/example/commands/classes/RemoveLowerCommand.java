package com.example.commands.classes;

import com.example.commands.interfaces.MovieArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class RemoveLowerCommand implements MovieArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {

        return null;
    }

    @Override
    public String describe() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
