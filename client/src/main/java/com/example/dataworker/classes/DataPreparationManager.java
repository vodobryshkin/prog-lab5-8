package com.example.dataworker.classes;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import domain.chat.classes.CommandBuffer;

import static com.example.client.modules.classes.ClientChatManager.authorized;

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
        if (authorized != null) {
            message += " " + authorized + " 1";
        }
        String[] tokens = message.split(" ");
        try {
            System.out.println(tokens[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
        }


        try {
            return validateManager.check(tokens);
        } catch (IncorrectInputException e) {
            throw new IncorrectInputException(e.getMessage());
        }

    }
}
