package com.example.api.input_entities.auth.login;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input.classes.input_strategies.KeyboardInput;
import com.example.api.input_entities.exceptions.IncorrectInputException;

import java.util.Objects;

public class LoginBuilderDirector {
    private InputManager inputManager;
    private LoginBuilder loginBuilder;

    public LoginBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        loginBuilder = new LoginBuilder();
    }

    public String getLogin() throws IncorrectInputException {
        loginBuilder.reset();

        try {
            if (!Objects.equals(inputManager.toString(), "FileInput")) {
                System.out.println("Введите логин:");
            }
            loginBuilder.setString(inputManager.readNext());
            return loginBuilder.getLogin();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return null;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getLogin();
            }
        }

    }
}