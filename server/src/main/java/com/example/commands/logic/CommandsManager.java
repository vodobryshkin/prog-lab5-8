package com.example.commands.logic;

import com.example.commands.classes.AddCommand;
import com.example.commands.classes.ExitCommand;
import com.example.commands.interfaces.Command;
import com.example.repository.interfaces.Repository;

import java.util.LinkedHashMap;

public class CommandsManager {
    LinkedHashMap<String, Command> commands = new LinkedHashMap<>();

    public CommandsManager(CollectionManager collectionManager) {
        commands.put("add", new AddCommand(collectionManager));
        commands.put("exit", new ExitCommand(collectionManager));
    }

    public Command get(String command) {
        return commands.get(command);
    }
}
