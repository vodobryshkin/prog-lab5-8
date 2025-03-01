package com.example.console_app.domain.commands.interfaces;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

/**
 • Интерфейс для команд, требующих ввода целочисленного аргумента.
 */
public interface IntInputable {

    /**
     * Пытается преобразовать строковый аргумент в целое число.
     *
     * @param arg Строковый аргумент, который нужно преобразовать в целое число.
     * @return Целочисленное значение, полученное из аргумента.
     * @throws IncorrectInputException Если аргумент не может быть преобразован в целое число или равен "null".
     */
    default int tryToInt(String arg) throws IncorrectInputException {
        int index;
        try {
            index = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            if (!arg.equals("null")) {
                throw new IncorrectInputException("Ошибка ввода: " + "\"" + arg + "\" значение аргумента команды не соответсвует типу int. ");
            } else {
                throw new IncorrectInputException("Ошибка ввода: значение аргумента команды не введено. ");
            }
        }
        return index;
    }
}
