package domain.chat.classes;

import entities.classes.Movie;

import java.io.Serializable;

public class CommandBuffer implements Serializable {
    private final String commandName;
    private String arg;
    private Movie movie;
    private String login;
    private String password;

    public CommandBuffer(String commandName) {
        this.commandName = commandName;
        this.arg = null;
        this.movie = null;
        this.login = null;
        this.password = null;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        if (movie != null) {
            return this.commandName + " " + this.login + this.password + this.arg + " " + this.movie;
        }
        return this.commandName + " " + this.login + this.password + this.arg;
    }
}
