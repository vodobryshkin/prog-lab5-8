package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class RemoveByIdCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
