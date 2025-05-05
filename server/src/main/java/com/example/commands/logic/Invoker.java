package com.example.commands.logic;

import com.example.repository.exceptions.KeyNotFoundException;
import domain.chat.classes.CommandBuffer;
import domain.chat.classes.ServerAnswerBuffer;

public class Invoker {
    private final CommandsManager commandsManager;

    public Invoker(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
    }

    public ServerAnswerBuffer getAnswer(CommandBuffer commandBuffer) throws KeyNotFoundException {
        System.out.println(commandBuffer.getArg());
        return commandsManager.get(commandBuffer.getCommandName()).execute(commandBuffer.getArg(), commandBuffer.getMovie(), commandBuffer.getLogin(), commandBuffer.getPassword());
    }
}
