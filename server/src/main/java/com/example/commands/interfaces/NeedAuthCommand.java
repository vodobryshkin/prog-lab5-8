package com.example.commands.interfaces;

public interface NeedAuthCommand {
    default boolean checkAuthStatus(String login, String password) {
        return login != null && password != null;
    }
}
