package com.example.console_app.api.text.classes;

import com.example.console_app.api.text.interfaces.TextManager;

public class ConsoleTextManager implements TextManager {
    @Override
    public void print(String inputString) {
        System.out.println(inputString);
    }
}
