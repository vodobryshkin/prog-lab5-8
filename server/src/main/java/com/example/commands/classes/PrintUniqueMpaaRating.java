package com.example.commands.classes;

import com.example.commands.interfaces.NoArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class PrintUniqueMpaaRating implements NoArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {

        return null;
    }

    @Override
    public String describe() {
        return "print_unique_mpaa_rating : вывести уникальные значения поля mpaaRating всех элементов в коллекции";
    }
}
