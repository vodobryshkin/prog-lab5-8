package com.example.commands.classes;

import com.example.commands.interfaces.NoArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class HelpCommand implements NoArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {

        return null;
    }

    @Override
    public String describe() {
        return "\n" +
                "\n" +
                "    help : вывести справку по доступным командам";
    }
}
