package com.example.commands.classes;

import com.example.commands.interfaces.AllArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class UpdateCommand implements AllArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {

        return null;
    }

    @Override
    public String describe() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
