package com.example.console_app.api.input.interfaces;

/**
 * Интерфейс InputStrategy создаёт шаблон для реализации классов для ввода данных.
 * <p> 
 * Этот интерфейс вводит описание того, как проверять наличие данных для отправления в поток ввода данных, отправлять данные в поток ввода данных.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public interface InputStrategy {
    /**
     * Проверка наличия данных для отправления в поток ввода данных.
     * 
     * @return true/false
     */
    boolean hasNext();

    /**
     * Отправка данных из потока ввода данных.
     * 
     * @return String
     */
    String readNext();
}
