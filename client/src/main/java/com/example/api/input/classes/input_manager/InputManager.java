package com.example.api.input.classes.input_manager;

import com.example.api.input.classes.input_strategies.KeyboardInput;
import com.example.api.input.interfaces.InputStrategy;

/**
 * Класс {@class InputManager} позволяет обращаться к общему потоку данных.
 * <p> 
 * Этот класс умеет проверять наличие данных для отправления в поток ввода данных, отправлять данные в поток ввода данных..
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class InputManager {
    private InputStrategy inputStrategy;
    
    /**
     * Конструктор класса {@class InputManager}.
     */
    public InputManager() {
        inputStrategy = new KeyboardInput();
    }

    /**
     * Геттер для параметра inputStrategy
     * 
     * @param inputStrategy
     */
    public void setInputStrategy(InputStrategy inputStrategy) {
        this.inputStrategy = inputStrategy;
    }
    
    /**
     * Проверка наличия данных для отправления в поток ввода данных.
     * 
     * @return true/false
     */
    public boolean hasNext() {
        return inputStrategy.hasNext();
    }

    /**
     * Отправка данных из потока ввода данных.
     * 
     * @return String
     */
    public String readNext() {
        return inputStrategy.readNext();
    }

    @Override
    public String toString() {
        return inputStrategy.toString();
    }
}
