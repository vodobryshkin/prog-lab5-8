package com.example.commands.classes;

import com.example.commands.interfaces.StringArgCommand;
import domain.chat.classes.ServerAnswerBuffer;
import domain.chat.enums.AnswerStatus;
import entities.classes.Movie;

public class ExecuteScriptCommand implements StringArgCommand {
    @Override
    public ServerAnswerBuffer execute(String arg, Movie movie) {
        return new ServerAnswerBuffer("execute_script", AnswerStatus.FILE, arg);
    }

    @Override
    public String describe() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
