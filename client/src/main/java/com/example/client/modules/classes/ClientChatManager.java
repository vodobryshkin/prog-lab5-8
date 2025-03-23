package com.example.client.modules.classes;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.client.modules.enums.Status;
import com.example.dataworker.classes.DataPreparationManager;
import domain.chat.classes.CommandBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientChatManager implements Runnable {
    private final ClientLoopManager loopManager;
    public static final InputManager inputManager = new InputManager();
    private final DataPreparationManager dataPreparationManager;

    public ClientChatManager(String host, int port, DatagramChannel channel, ByteBuffer buffer) {
        loopManager = new ClientLoopManager(new ClientSenderManager(host, port, channel, buffer),
                new ClientReceiverManager(channel, buffer),
                new ClientDescriptionManager(buffer));
        dataPreparationManager = new DataPreparationManager();
    }

    @Override
    public void run() {
        while (inputManager.hasNext()) {
            String message = inputManager.readNext();

            dataPreparationManager.setMessage(message);
            CommandBuffer serializedMessage;
            try {
                serializedMessage = dataPreparationManager.prepare();
            } catch (IncorrectInputException e) {
                System.out.println(e.getMessage() + " Повторите попытку ввода.");
                continue;
            }

            Status status = Status.REJECTED_RECEIVE;
            while (status == Status.REJECTED_RECEIVE) {
                try {
                    status = loopManager.ask(serializedMessage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (status == Status.REJECTED_RECEIVE) {
                    System.out.println("Error while receiving message \"" + message + "\" from server. Retry will be in 8 seconds.");
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        System.err.println("Thread was interrupted: " + e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}
