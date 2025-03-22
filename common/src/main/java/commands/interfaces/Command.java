package commands.interfaces;

import entities.classes.Movie;

import java.io.Serializable;

public interface Command extends Serializable {
    void execute(String arg, Movie movie);
    String describe();
}
