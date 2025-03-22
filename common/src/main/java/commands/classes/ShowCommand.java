package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class ShowCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
