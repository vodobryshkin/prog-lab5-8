package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class MaxByCreationDateCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "max_by_creation_date : вывести любой объект из коллекции, значение поля creationDate которого является максимальным";
    }
}
