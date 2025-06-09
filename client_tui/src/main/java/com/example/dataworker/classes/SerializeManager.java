package com.example.dataworker.classes;

import com.example.api.input_entities.auth.login.LoginBuilderDirector;
import com.example.api.input_entities.auth.password.PasswordBuilderDirector;
import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.movie.MovieBuilderDirector;
import com.example.client.modules.classes.ClientChatManager;
import domain.chat.classes.CommandBuffer;
import entities.classes.Movie;

import static com.example.client.modules.classes.ClientChatManager.authorized;

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

        if (!neededFlags[2] && !neededFlags[3]) {
            commandBuffer.setLogin(new LoginBuilderDirector(ClientChatManager.inputManager).getLogin());
            commandBuffer.setPassword(new PasswordBuilderDirector(ClientChatManager.inputManager).getPassword());
        }

        if (neededFlags[2] && neededFlags[3]) {
            try {
                commandBuffer.setLogin(tokens[1]);
                commandBuffer.setPassword(tokens[2]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IncorrectInputException("Для выполнения команды необходимо выполнить вход. Воспользуйтесь командами auth/register для авторизации/регистрации.");
            }
        }

        if (neededFlags[0]) {
            try {
                commandBuffer.setArg(tokens[3]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IncorrectInputException("Команда не принимает нужный аргумент на той же строке.");
            }
        }

        if (neededFlags[1]) {
            Movie movie = new MovieBuilderDirector(ClientChatManager.inputManager).inputMovie();
            movie.setUserLogin(authorized);
            commandBuffer.setLogin(authorized);
            commandBuffer.setMovie(movie);
        }

        return commandBuffer;

    }
}
