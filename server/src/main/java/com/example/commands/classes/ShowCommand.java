package com.example.commands.classes;

import com.example.commands.interfaces.NoArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class ShowCommand implements NoArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {

        return null;
    }

    @Override
    public String describe() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
