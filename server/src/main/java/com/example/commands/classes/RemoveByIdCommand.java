package com.example.commands.classes;

import com.example.commands.interfaces.StringArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class RemoveByIdCommand implements StringArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {

        return null;
    }

    @Override
    public String describe() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
