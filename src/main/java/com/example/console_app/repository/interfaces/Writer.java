package com.example.console_app.repository.interfaces;

import com.example.console_app.repository.csv.CsvRepository;
import com.example.console_app.repository.exceptions.KeyNotFoundException;


/**
 * Интерфейс, который записывает в файл.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public interface Writer {
    public void write(CsvRepository csvRepository) throws KeyNotFoundException ;
}
