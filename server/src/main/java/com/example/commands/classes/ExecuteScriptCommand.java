package com.example.commands.classes;

import com.example.commands.interfaces.StringArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import entities.classes.Movie;

public class ExecuteScriptCommand implements StringArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {

        return null;
    }

    @Override
    public String describe() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
