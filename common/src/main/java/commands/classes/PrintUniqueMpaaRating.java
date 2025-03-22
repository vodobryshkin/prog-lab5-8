package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class PrintUniqueMpaaRating implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "print_unique_mpaa_rating : вывести уникальные значения поля mpaaRating всех элементов в коллекции";
    }
}
