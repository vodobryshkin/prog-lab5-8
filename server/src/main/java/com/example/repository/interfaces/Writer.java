package com.example.repository.interfaces;

import com.example.repository.csv.CsvRepository;
import com.example.repository.exceptions.KeyNotFoundException;


/**
 * Интерфейс, который записывает в файл.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public interface Writer {
    void write(Repository repository) throws KeyNotFoundException ;
}
