package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class SaveCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "save : сохранить коллекцию в файл";
    }
}
