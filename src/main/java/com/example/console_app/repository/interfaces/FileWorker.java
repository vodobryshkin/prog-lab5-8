/**
 * Интерфейс для работы с файловой системой.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
package com.example.console_app.repository.interfaces;

import com.example.console_app.repository.exceptions.FileNotFoundException;

public interface FileWorker {
    /**
     * Возвращает путь к файлу.
     *
     * @return строка, представляющая путь к файлу
     * @throws FileNotFoundException если файл не найден
     */
    public String getPath() throws FileNotFoundException;
}