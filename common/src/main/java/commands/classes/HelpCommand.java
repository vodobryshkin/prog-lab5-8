package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class HelpCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "\n" +
                "\n" +
                "    help : вывести справку по доступным командам";
    }
}
