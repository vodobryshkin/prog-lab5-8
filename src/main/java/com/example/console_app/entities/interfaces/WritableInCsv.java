package com.example.console_app.entities.interfaces;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Интерфейс, определяющий метод для преобразования объекта в строковое представление в формате CSV.
 */
public interface WritableInCsv {

    /**
     * Преобразует объект в строковое представление в формате CSV.
     *
     * @return Строка, представляющая объект в формате CSV.
     */
    public String toCsv();
}
