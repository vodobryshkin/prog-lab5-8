package com.example.commands.classes;

import com.example.commands.interfaces.StringArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class RemoveAtIndexCommand implements StringArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {

        return null;
    }

    @Override
    public String describe() {
        return "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)";
    }
}
