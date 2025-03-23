package com.example.commands.classes;

import com.example.commands.interfaces.NoArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class InfoCommand implements NoArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {

        return null;
    }

    @Override
    public String describe() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
