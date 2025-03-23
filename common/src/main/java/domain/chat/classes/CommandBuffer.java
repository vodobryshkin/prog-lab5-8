package domain.chat.classes;

import entities.classes.Movie;

import java.io.Serializable;

public class CommandBuffer implements Serializable {
    private final String commandName;
    private String arg;
    private Movie movie;

    public CommandBuffer(String commandName) {
        this.commandName = commandName;
        this.arg = null;
        this.movie = null;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getArg() {
        return arg;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        if (movie != null) {
            return this.commandName + " " + this.arg + " " + this.movie;
        }
        return this.commandName + " " + this.arg;
    }
}
