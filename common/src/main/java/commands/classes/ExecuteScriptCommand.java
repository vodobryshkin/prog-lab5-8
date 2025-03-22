package commands.classes;

import commands.interfaces.Command;
import entities.classes.Movie;

public class ExecuteScriptCommand implements Command {
    @Override
    public void execute(String arg, Movie movie) {

    }

    @Override
    public String describe() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
