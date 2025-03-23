package com.example.commands.interfaces;

import entities.classes.Movie;

public interface AllArgCommand extends Command {
    default boolean check(String arg, Movie movie) {
        return arg != null && movie != null;
    }
}
