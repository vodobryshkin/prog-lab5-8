package com.example.console_app;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.client.Client;
import com.example.console_app.domain.commands.exceptions.EmptyCollectionException;
import com.example.console_app.domain.commands.exceptions.StackRepeatException;
import com.example.console_app.repository.exceptions.KeyNotFoundException;

public class Main {
    public static void main(String[] args) throws IncorrectInputException, KeyNotFoundException, EmptyCollectionException, StackRepeatException {
        Client client = new Client();
        client.run();
    }
}
