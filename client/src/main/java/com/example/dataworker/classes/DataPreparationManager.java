package com.example.dataworker.classes;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import domain.chat.classes.CommandBuffer;

public class DataPreparationManager {
    private final ValidateManager validateManager;
    private String message;

    public DataPreparationManager() {
        this.validateManager = new ValidateManager();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CommandBuffer prepare() throws IncorrectInputException {
        String[] tokens = message.split(" ");

        try {
            return validateManager.check(tokens);
        } catch (IncorrectInputException e) {
            throw new IncorrectInputException(e.getMessage());
        }

    }
}
