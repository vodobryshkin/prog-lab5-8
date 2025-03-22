package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class UpdateCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
