package com.example.console_app.client;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input.classes.input_strategies.KeyboardInput;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.text.classes.ConsoleTextManager;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.exceptions.EmptyCollectionException;
import com.example.console_app.domain.commands.exceptions.StackRepeatException;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.domain.logic.ExecutingStack;
import com.example.console_app.domain.logic.Invoker;
import com.example.console_app.repository.csv.CsvRepository;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

/**
 * Класс Client предоставляет доступ к клиентскому уровню приложения.
 * <p> 
 * Этот интерфейс умеет запускать работу программы.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class Client {
    private Invoker invoker;
    private CommandManager commands;
    private final InputManager inputManager;
    private final ExecutingStack executingStack;
    private final TextManager textManager;
    /**
     * Конструктор класса  Client.
     */
    public Client() {
        invoker = new Invoker(commands);
        inputManager = new InputManager();
        CollectionManager collectionManager = new CollectionManager(new CsvRepository(), inputManager);
        executingStack = new ExecutingStack();
        textManager = new ConsoleTextManager();
        commands = new CommandManager(collectionManager, executingStack, inputManager, textManager);
        invoker = new Invoker(commands);
    }
    /**
     * Запуск программы.
     */
    public void run() throws KeyNotFoundException, IncorrectInputException, EmptyCollectionException, StackRepeatException {

        while (inputManager.hasNext()) {
            String line = inputManager.readNext();
            String[] tokens = line.split(" ");
        
            if (tokens.length == 1) {
                line += " null";
                tokens = line.split(" ");
            }
                
            try {
                if (!tokens[0].isEmpty()) {
                    invoker.commandExecute(tokens);
                }
            } catch (NullPointerException e) {
                if (!tokens[0].isEmpty()) {
                    textManager.print(tokens[0] + ": такой команды не существует. Повторите попытку ввода.");
                }
            } catch (IncorrectInputException | KeyNotFoundException | EmptyCollectionException e) {
                if (!inputManager.toString().equals("FileInput"))
                {
                    textManager.print(e.getMessage() + "Повторите попытку ввода.");
                } else {
                    textManager.print(e.getMessage() + "Действие скрипта завершено.");
                }
                
            } catch (StackRepeatException e) {
                textManager.print(e.getMessage() + "Действие скрипта завершено.");
            } 

            if (!inputManager.hasNext() && inputManager.toString().equals("FileInput")) {
                inputManager.setInputStrategy(new KeyboardInput());
                executingStack.clear();
            }
        }
    }
}
