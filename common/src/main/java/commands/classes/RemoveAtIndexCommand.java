package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class RemoveAtIndexCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)";
    }
}
