/**
 * Интерфейс для работы с репозиторием фильмов (Movie).
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
package com.example.repository.interfaces;

import entities.classes.Movie;
import com.example.repository.exceptions.KeyNotFoundException;

import java.util.ArrayList;

public interface Repository {
    /**
     * Возвращает объект Movie по указанному идентификатору.
     *
     * @param id идентификатор фильма
     * @return объект Movie, соответствующий указанному идентификатору
     * @throws KeyNotFoundException если объект с указанным идентификатором не найден
     */
    Movie getById(int id) throws KeyNotFoundException;

    /**
     * Добавляет новый объект Movie в репозиторий.
     *
     * @param movie объект Movie для добавления
     */
    void add(Movie movie) throws KeyNotFoundException;

    /**
     * Обновляет объект Movie с указанным идентификатором.
     *
     * @param id идентификатор фильма для обновления
     * @param movie новый объект Movie
     * @throws KeyNotFoundException если объект с указанным идентификатором не найден
     */
    void update(int id, Movie movie) throws KeyNotFoundException;

    /**
     * Удаляет объект Movie с указанным идентификатором.
     *
     * @param id идентификатор фильма для удаления
     * @throws KeyNotFoundException если объект с указанным идентификатором не найден
     */
    void delete(int id) throws KeyNotFoundException;

    /**
     * Записывает данные репозитория в файл.
     *
     * @throws KeyNotFoundException если возникла ошибка при записи данных
     */
    void writeIntoFile() throws KeyNotFoundException;

    /**
     * Возвращает объект Movie по указанному индексу.
     *
     * @param index индекс элемента в репозитории
     * @return объект Movie, находящийся по указанному индексу
     * @throws KeyNotFoundException если элемент с указанным индексом не найден
     */
    Movie getByIndex(int index) throws KeyNotFoundException;

    /**
     * Возвращает количество элементов в репозитории.
     *
     * @return количество элементов в репозитории
     */
    int getSize();

    ArrayList<Movie> returnAll() throws KeyNotFoundException;
    void clear();
}