package com.example.console_app.api.input.classes.input_strategies;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.console_app.api.input.interfaces.InputStrategy;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

/**
 * Класс {@class FileInput} реализует функции для ввода данных из файла.
 * <p> 
 * Этот класс умеет проверять наличие данных для отправления в поток ввода данных, отправлять данные в поток ввода данных.
 * </p>
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class FileInput implements InputStrategy {
    private BufferedInputStream inputStream;
    private StringBuilder buffer;
    private boolean endOfFile;

    /**
     * Конструктор класса {@class FileInput}.
     */
    public FileInput(String filePath) throws IncorrectInputException {
        try {
            this.inputStream = new BufferedInputStream(new FileInputStream(filePath));
            this.buffer = new StringBuilder();
            this.endOfFile = false;
        } catch (IOException e) {
            this.endOfFile = true;
            throw new IncorrectInputException("Ошибка: " + filePath + " файла с таким именем не существует.");
        }
    }

    /**
     * Проверка наличия данных для отправления в поток ввода данных.
     * 
     * @return true/false
     */
    @Override
    public boolean hasNext() {
        if (endOfFile) {
            return false;
        }
        try {
            if (inputStream.available() > 0) {
                return true;
            } else {
                endOfFile = true;
                inputStream.close();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            endOfFile = true;
            return false;
        }
    }

    /**
     * Отправка данных из потока ввода данных.
     * 
     * @return String
     */
    @Override
    public String readNext() {
        if (endOfFile) {
            return null;
        }
        try {
            int data;
            buffer.setLength(0);
            while ((data = inputStream.read()) != -1) {
                if (data == '\n' || data == '\r') {
                    break;
                }
                buffer.append((char) data);
            }
            if (data == -1) {
                endOfFile = true;
                inputStream.close();
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            endOfFile = true;
            return null;
        }
    }

    public void setFilePath(String path) throws FileNotFoundException {
        try {
            this.inputStream = new BufferedInputStream(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            this.endOfFile = true;
        }
    }

    @Override
    public String toString() {
        return "FileInput";
    }
}