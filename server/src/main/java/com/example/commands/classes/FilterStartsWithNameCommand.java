package com.example.commands.classes;

import com.example.commands.interfaces.StringArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class FilterStartsWithNameCommand implements StringArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {

        return null;
    }

    @Override
    public String describe() {
        return "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}
