package com.example.api.input.classes.input_strategies;

import java.util.Scanner;

import com.example.api.input.interfaces.InputStrategy;

/**
 * Класс {@class KeyboardInput} реализует функции для ввода данных с клавиатуры.
 * <p> 
 * Этот класс умеет проверять наличие данных для отправления в поток ввода данных, отправлять данные в поток ввода данных.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class KeyboardInput implements InputStrategy {
    private Scanner scanner;

    /**
     * Конструктор класса {@class KeyboardInput}.
     */
    public KeyboardInput() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Проверка наличия данных для отправления в поток ввода данных.
     * 
     * @return true/false
     */
    @Override
    public boolean hasNext() {
        return scanner.hasNext();
    }
    
    /**
     * Отправка данных из потока ввода данных.
     * 
     * @return String
     */
    @Override
    public String readNext() {
        return scanner.nextLine();
    }

    @Override
    public String toString() {
        return "KeyboardInput";
    }
}
