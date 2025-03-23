package com.example.repository.interfaces;

import java.util.ArrayList;

import entities.classes.Movie;

/**
 * Интерфейс, который читает файл.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public interface Reader {
    ArrayList<Movie> read();
}
