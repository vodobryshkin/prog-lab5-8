package com.example.commands.classes;

import com.example.commands.interfaces.Command;
import com.example.commands.interfaces.NoArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import domain.chat.enums.AnswerStatus;
import entities.classes.Movie;

import java.util.LinkedHashMap;

public class HelpCommand implements NoArgCommand {
    private final LinkedHashMap<String, Command> commands;

    public HelpCommand(LinkedHashMap<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie, String login, String password) {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("\nВсе доступные команды:\n");

        for (String commandName : commands.keySet()) {
            Command command = commands.get(commandName);
            helpMessage.append(command.describe()).append("\n");
        }

        return new ServerAnswerBuffer("help", AnswerStatus.OK, helpMessage.toString());
    }

    @Override
    public String describe() {
        return "help : вывести справку по доступным командам";
    }
}