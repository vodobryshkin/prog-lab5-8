package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class RemoveLowerCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
