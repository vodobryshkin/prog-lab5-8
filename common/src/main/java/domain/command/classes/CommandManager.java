package domain.command.classes;

import exceptions.IncorrectInputException;

import java.util.LinkedHashMap;

public class CommandManager {
    LinkedHashMap<String, boolean[]> commands = new LinkedHashMap<>();

    public CommandManager() {
        commands.put("help", new boolean[]{false, false, true, true});
        commands.put("auth", new boolean[]{false, false, false, false});
        commands.put("register", new boolean[]{false, false, false, false});
        commands.put("info", new boolean[]{false, false, true, true});
        commands.put("show", new boolean[]{false, false, true, true});
        commands.put("add", new boolean[]{false, true, true, true});
        commands.put("update", new boolean[]{true, true, true, true});
        commands.put("remove_by_id", new boolean[]{true, false, true, true});
        commands.put("clear", new boolean[]{false, false, true, true});
        commands.put("execute_script", new boolean[]{true, false, true, true});
        commands.put("exit", new boolean[]{false, false, true, true});
        commands.put("remove_at", new boolean[]{true, false, true, true});
        commands.put("remove_first", new boolean[]{false, false, true, true});
        commands.put("remove_lower", new boolean[]{false, true, true, true});
        commands.put("max_by_creation_date", new boolean[]{false, false, true, true});
        commands.put("filter_starts_with_name", new boolean[]{true, false, true, true});
        commands.put("print_unique_mpaa_rating", new boolean[]{false, false, true, true});
    }

    public boolean[] getConfiguration(String arg) throws IncorrectInputException {
        return commands.get(arg);
    }
}
