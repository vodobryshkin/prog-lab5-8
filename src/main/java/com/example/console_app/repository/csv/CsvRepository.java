/**
 * Класс для работы с репозиторием фильмов, хранящимся в CSV-файле.
 * Реализует интерфейсы {@link Repository} и {@link ArrayManipulator}.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
package com.example.console_app.repository.csv;

import java.util.ArrayList;

import com.example.console_app.entities.classes.Movie;
import com.example.console_app.repository.csv.adapters.CsvReader;
import com.example.console_app.repository.csv.adapters.CsvWriter;
import com.example.console_app.repository.exceptions.KeyNotFoundException;
import com.example.console_app.repository.interfaces.ArrayManipulator;
import com.example.console_app.repository.interfaces.Repository;

public class CsvRepository implements Repository, ArrayManipulator {
    private final ArrayList<Movie> collection;
    private final CsvWriter csvWriter;

    /**
     * Конструктор по умолчанию. Инициализирует коллекцию фильмов, читая данные из CSV-файла.
     */
    public CsvRepository() {
        collection = new CsvReader().read();
        csvWriter = new CsvWriter();
    }

    /**
     * Возвращает фильм по его идентификатору.
     * 
     * @param id Идентификатор фильма.
     * @return Объект {@link Movie}, соответствующий указанному идентификатору.
     * @throws KeyNotFoundException Если фильм с указанным идентификатором не найден.
     */
    @Override
    public Movie getById(int id) throws KeyNotFoundException {
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId() == id) {
                return collection.get(i);
            }
        }
        throw new KeyNotFoundException("Ошибка: " + id + " - элемента с таким id не существует.");
    }

    /**
     * Добавляет новый фильм в коллекцию.
     * 
     * @param movie Объект {@link Movie} для добавления.
     */
    @Override
    public void add(Movie movie) {
        collection.add(movie);
    }

    /**
     * Обновляет фильм в коллекции по его идентификатору.
     * 
     * @param id Идентификатор фильма для обновления.
     * @param movie Новый объект {@link Movie}.
     * @throws KeyNotFoundException Если фильм с указанным идентификатором не найден.
     */
    @Override
    public void update(int id, Movie movie) throws KeyNotFoundException {
        int index = -1;

        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId() == id) {
                index = i;
            }
        }

        if (index == -1) {
            throw new KeyNotFoundException("Ошибка: " + id + " - элемента с таким id не существует.");
        }

        collection.set(index, movie);
    }

    /**
     * Удаляет фильм из коллекции по его идентификатору.
     * 
     * @param id Идентификатор фильма для удаления.
     * @throws KeyNotFoundException Если фильм с указанным идентификатором не найден.
     */
    @Override
    public void delete(int id) throws KeyNotFoundException {
        int index = -1;

        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId() == id) {
                index = i;
            }
        }
        if (index == -1) {
            throw new KeyNotFoundException("Ошибка: " + id + " - элемента с таким id не существует.");
        }

        collection.remove(index);
    }

    /**
     * Возвращает фильм по его индексу в коллекции.
     * 
     * @param index Индекс фильма в коллекции.
     * @return Объект {@link Movie}, соответствующий указанному индексу.
     * @throws KeyNotFoundException Если индекс выходит за пределы коллекции.
     */
    @Override
    public Movie getByIndex(int index) throws KeyNotFoundException {
        try {
            return collection.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new KeyNotFoundException("Ошибка: " + index + " - элемента с таким id не существует.");
        }
    }

    /**
     * Возвращает размер коллекции фильмов.
     * 
     * @return Количество фильмов в коллекции.
     */
    @Override
    public int getSize() {
        return collection.size();
    }

    /**
     * Записывает данные коллекции в CSV-файл.
     * 
     * @throws KeyNotFoundException Если возникает ошибка при записи данных.
     */
    @Override
    public void writeIntoFile() throws KeyNotFoundException {
        csvWriter.write(this);
    }

    /**
     * Возвращает строковое представление коллекции.
     * 
     * @return Имя класса коллекции.
     */
    @Override
    public String toString() {
        return collection.getClass().getSimpleName();
    }
}