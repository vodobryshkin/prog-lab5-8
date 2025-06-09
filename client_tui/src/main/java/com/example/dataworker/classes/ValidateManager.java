package com.example.dataworker.classes;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import domain.chat.classes.CommandBuffer;
import domain.command.classes.CommandManager;

public class ValidateManager {
    private final CommandManager commandManager;

    public ValidateManager() {
        commandManager = new CommandManager();
    }

    public CommandBuffer check(String[] tokens) throws IncorrectInputException {
        boolean[] result = commandManager.getConfiguration(tokens[0]);

        SerializeManager serializeManager = new SerializeManager(new CommandBuffer(tokens[0]), result, tokens);
        try {
            return serializeManager.getCommandBuffer();
        }
        catch (IncorrectInputException e) {
            throw new IncorrectInputException(e.getMessage());
        }

    }
}
