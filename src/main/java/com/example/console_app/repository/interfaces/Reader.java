package com.example.console_app.repository.interfaces;

import java.util.ArrayList;

import com.example.console_app.entities.classes.Movie;

/**
 * Интерфейс, который читает файл.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public interface Reader {
    public ArrayList<Movie> read();
}
