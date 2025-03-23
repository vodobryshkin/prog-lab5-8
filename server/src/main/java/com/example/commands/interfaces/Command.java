package com.example.commands.interfaces;

import com.example.repository.exceptions.KeyNotFoundException;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public interface Command {
    ServerAnswerBuffer execute(String arg, Movie movie) throws KeyNotFoundException;
    boolean check(String arg, Movie movie);
    String describe();
}
