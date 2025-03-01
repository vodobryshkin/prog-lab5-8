package com.example.console_app.domain.commands.classes.no_manager;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input.classes.input_strategies.FileInput;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.domain.commands.exceptions.StackRepeatException;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.logic.ExecutingStack;

/**
 * Класс {@class ExecuteScriptCommand} реализует команду исполнения скрипта из файла и описывает её.
 * <p> 
 * Этот класс исполняет команду исполнения скрипта из файла и описывает её.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class ExecuteScriptCommand implements Command {
    private final InputManager inputManager;
    private final ExecutingStack executingStack;
    
    /**
     * Конструктор класса {@class ExecuteScriptCommand}.
     */
    public ExecuteScriptCommand(InputManager inputManager, ExecutingStack executingStack) {
        this.inputManager = inputManager;
        this.executingStack = executingStack;
    }

    /**
     * Исполнение команды с заданным аргументом.
     */
    @Override
    public void execute(String arg) throws StackRepeatException, IncorrectInputException {
        System.out.println(executingStack);
        if (executingStack.isEmpty()) {
            executingStack.push(arg);
            inputManager.setInputStrategy(new FileInput(arg));
        } else {
            if (!executingStack.inStack(arg)) {
                executingStack.push(arg);
                inputManager.setInputStrategy(new FileInput(arg));
            } else {
                throw new StackRepeatException("Ошибка: повторное выполнение скрипта " + arg + " приведет к зацикливанию программы. ");
            }
        }
    }

    /**
     * Описание того, что делает команда
     */
    @Override
    public String describe() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
