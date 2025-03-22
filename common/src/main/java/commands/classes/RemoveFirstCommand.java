package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class RemoveFirstCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "remove_first : удалить первый элемент из коллекции";
    }
}
