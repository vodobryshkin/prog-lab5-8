/**
 * Класс CommandManager управляет командами, доступными для выполнения в приложении.
 * Реализует связь между командами и их выполнением через коллекцию команд.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-25-02
 */
package com.example.console_app.client;

import java.util.LinkedHashMap;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.text.interfaces.TextManager;
import com.example.console_app.domain.commands.classes.manager.AddCommand;
import com.example.console_app.domain.commands.classes.manager.ClearCommand;
import com.example.console_app.domain.commands.classes.manager.FilterStartsWithNameCommand;
import com.example.console_app.domain.commands.classes.manager.InfoCommand;
import com.example.console_app.domain.commands.classes.manager.MaxByCreationDateCommand;
import com.example.console_app.domain.commands.classes.manager.PrintUniqueMpaaRatingCommand;
import com.example.console_app.domain.commands.classes.manager.RemoveAtCommand;
import com.example.console_app.domain.commands.classes.manager.RemoveByIdCommand;
import com.example.console_app.domain.commands.classes.manager.RemoveFirstCommand;
import com.example.console_app.domain.commands.classes.manager.RemoveLowerCommand;
import com.example.console_app.domain.commands.classes.manager.SaveCommand;
import com.example.console_app.domain.commands.classes.manager.ShowCommand;
import com.example.console_app.domain.commands.classes.manager.UpdateCommand;
import com.example.console_app.domain.commands.classes.no_manager.ExecuteScriptCommand;
import com.example.console_app.domain.commands.classes.no_manager.ExitCommand;
import com.example.console_app.domain.commands.classes.no_manager.HelpCommand;
import com.example.console_app.domain.commands.interfaces.Command;
import com.example.console_app.domain.logic.CollectionManager;
import com.example.console_app.domain.logic.ExecutingStack;

public class CommandManager {
    private final LinkedHashMap<String, Command> commands;

    /**
     * Конструктор класса CommandManager.
     *
     * @param collectionManager Менеджер коллекции, используемый для выполнения команд.
     * @param executingStack Стек выполнения для управления скриптами.
     * @param inputManager Менеджер ввода данных.
     */
    public CommandManager(CollectionManager collectionManager, ExecutingStack executingStack, InputManager inputManager, TextManager textManager) {
        commands = new LinkedHashMap<>();

        // Инициализация команд
        commands.put("help", new HelpCommand(commands, textManager));
        commands.put("info", new InfoCommand(collectionManager, textManager));
        commands.put("show", new ShowCommand(collectionManager, textManager));
        commands.put("add", new AddCommand(collectionManager, textManager));
        commands.put("update", new UpdateCommand(collectionManager, textManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager, textManager));
        commands.put("save", new SaveCommand(collectionManager, textManager));
        commands.put("clear", new ClearCommand(collectionManager, textManager));
        commands.put("execute_script", new ExecuteScriptCommand(inputManager, executingStack));
        commands.put("exit", new ExitCommand(textManager));
        commands.put("remove_at", new RemoveAtCommand(collectionManager, textManager));
        commands.put("remove_first", new RemoveFirstCommand(collectionManager, textManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager, textManager));
        commands.put("max_by_creation_date", new MaxByCreationDateCommand(collectionManager, textManager));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(collectionManager, textManager));
        commands.put("print_unique_mpaa_rating", new PrintUniqueMpaaRatingCommand(collectionManager, textManager));
    }

    /**
     * Возвращает команду по её имени.
     *
     * @param arg Имя команды.
     * @return Команда, соответствующая указанному имени, или null, если команда не найдена.
     */
    public Command get(String arg) {
        return commands.get(arg);
    }
}