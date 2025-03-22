package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class AddCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
