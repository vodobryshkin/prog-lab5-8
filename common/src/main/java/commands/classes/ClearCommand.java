package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class ClearCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "clear : очистить коллекцию";
    }
}
