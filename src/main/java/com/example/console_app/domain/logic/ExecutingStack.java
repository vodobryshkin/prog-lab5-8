/**
 * Класс ExecutingStack представляет собой реализацию стека на основе ArrayList.
 * Стек поддерживает основные операции: добавление элемента, удаление элемента,
 * проверка наличия элемента, очистка стека и проверка на пустоту.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-25-02
 */
package com.example.console_app.domain.logic;

import java.util.ArrayList;

public class ExecutingStack {
    private ArrayList<String> stack;

    /**
     * Конструктор по умолчанию. Инициализирует пустой стек.
     */
    public ExecutingStack() {
        stack = new ArrayList<String>();
    }

    /**
     * Добавляет элемент на вершину стека.
     *
     * @param s Элемент для добавления в стек.
     */
    public void push(String s) {
        stack.add(s);
    }

    /**
     * Удаляет элемент с вершины стека, если стек не пуст.
     */
    public void pop() {
        if (stack.size() > 0) {
            stack.remove(stack.size() - 1);
        }
    }

    /**
     * Очищает стек, удаляя все элементы.
     */
    public void clear() {
        while (stack.size() > 0) {
            stack.remove(stack.size() - 1);
        }
    }

    /**
     * Проверяет, содержится ли указанный элемент в стеке.
     *
     * @param s Элемент для поиска в стеке.
     * @return true, если элемент найден в стеке, иначе false.
     */
    public boolean inStack(String s) {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i).equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверяет, пуст ли стек.
     *
     * @return true, если стек пуст, иначе false.
     */
    public boolean isEmpty() {
        return stack.size() == 0;
    }

    /**
     * Возвращает строковое представление стека.
     *
     * @return Строка, представляющая содержимое стека.
     */
    @Override
    public String toString() {
        return stack.toString();
    }
}