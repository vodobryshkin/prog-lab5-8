package com.example.commands.classes;

import com.example.commands.db.UsersManager;
import com.example.commands.interfaces.Command;
import com.example.repository.exceptions.KeyNotFoundException;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class RegisterCommand implements Command {
    private final UsersManager usersManager;

    public RegisterCommand(UsersManager usersManager) {
        this.usersManager = usersManager;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie, String login, String password) throws KeyNotFoundException {
        return usersManager.register("register", login, password);
    }

    @Override
    public boolean check(String arg, Movie movie) {
        return false;
    }

    @Override
    public String describe() {
        return "register: создать аккаунт на платформе";
    }
}
