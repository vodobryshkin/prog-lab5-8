package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class ExitCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
