package com.example.client.modules.classes;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.client.modules.enums.Status;

import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientChatManager implements Runnable {
    private final ClientLoopManager loopManager;
    private final InputManager inputManager;

    public ClientChatManager(String host, int port, DatagramChannel channel, ByteBuffer buffer) {
        inputManager = new InputManager();
        loopManager = new ClientLoopManager(new ClientSenderManager(host, port, channel, buffer),
                new ClientReceiverManager(channel, buffer),
                new ClientDescriptionManager(buffer));
    }

    @Override
    public void run() {
        while (inputManager.hasNext()) {
            String message = inputManager.readNext();

            Status status = Status.REJECTED_RECEIVE;
            while (status == Status.REJECTED_RECEIVE) {
                status = loopManager.ask(message);

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
