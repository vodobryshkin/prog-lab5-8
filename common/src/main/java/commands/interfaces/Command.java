package commands.interfaces;

import entities.classes.Movie;

public interface Command {
    void execute(String arg, Movie movie);
    String describe();
}
