package com.example.commands.logic;

import com.example.commands.classes.*;
import com.example.commands.db.UsersManager;
import com.example.commands.interfaces.Command;

import java.util.LinkedHashMap;

public class CommandsManager {
    LinkedHashMap<String, Command> commands = new LinkedHashMap<>();

    public CommandsManager(CollectionManager collectionManager) {
        commands.put("help", new HelpCommand(commands));
        commands.put("auth", new AuthCommand(new UsersManager()));
        commands.put("register", new RegisterCommand(new UsersManager()));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager));
        commands.put("update", new UpdateCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("exit", new ExitCommand(collectionManager));
        commands.put("remove_at", new RemoveAtIndexCommand(collectionManager));
        commands.put("remove_first", new RemoveFirstCommand(collectionManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager));
        commands.put("max_by_creation_date", new MaxByCreationDateCommand(collectionManager));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(collectionManager));
        commands.put("print_unique_mpaa_rating", new PrintUniqueMpaaRating(collectionManager));
    }

    public Command get(String command) {
        return commands.get(command);
    }
}
