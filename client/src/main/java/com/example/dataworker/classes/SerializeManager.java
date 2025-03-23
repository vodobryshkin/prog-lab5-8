package com.example.dataworker.classes;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.movie.MovieBuilderDirector;
import com.example.client.modules.classes.ClientChatManager;
import domain.chat.classes.CommandBuffer;

public class SerializeManager {
    private final CommandBuffer commandBuffer;
    private final boolean[] neededFlags;
    private final String[] tokens;

    public SerializeManager(CommandBuffer commandBuffer, boolean[] neededFlags, String[] tokens) {
        this.commandBuffer = commandBuffer;
        this.neededFlags = neededFlags;
        this.tokens = tokens;
    }

    public CommandBuffer getCommandBuffer() throws IncorrectInputException {
        if (neededFlags == null || commandBuffer.getCommandName().isEmpty()) {
            throw new IncorrectInputException("Команды \"" + commandBuffer.getCommandName() + "\" не существует.");
        }
        if (neededFlags[0]) {
            try {
                commandBuffer.setArg(tokens[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IncorrectInputException("Функция не принимает нужный аргумент на той же строке.");
            }

        }

        if (neededFlags[1]) {
            commandBuffer.setMovie(new MovieBuilderDirector(ClientChatManager.inputManager).inputMovie());
        }

        return commandBuffer;

    }
}
