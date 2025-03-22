package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class FilterStartsWithNameCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}
