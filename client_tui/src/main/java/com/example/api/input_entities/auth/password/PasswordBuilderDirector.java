package com.example.api.input_entities.auth.password;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input.classes.input_strategies.KeyboardInput;
import com.example.api.input_entities.exceptions.IncorrectInputException;

import java.util.Objects;

public class PasswordBuilderDirector {
    private InputManager inputManager;
    private PasswordBuilder passwordBuilder;

    public PasswordBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        passwordBuilder = new PasswordBuilder();
    }

    public String getPassword() throws IncorrectInputException {
        passwordBuilder.reset();

        try {
            if (!Objects.equals(inputManager.toString(), "FileInput")) {
                System.out.println("Введите пароль:");
            }
            passwordBuilder.setString(inputManager.readNext());
            return passwordBuilder.getPassword();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return null;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getPassword();
            }
        }

    }
}
