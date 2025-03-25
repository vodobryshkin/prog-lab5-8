package com.example.client.modules.classes;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input.classes.input_strategies.FileInput;
import com.example.client.modules.interfaces.ClientDescriptor;
import domain.chat.classes.ServerAnswerBuffer;
import domain.chat.enums.AnswerStatus;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class ClientDescriptionManager implements ClientDescriptor {
    private final InputManager inputManager;
    private final ByteBuffer buffer;

    public ClientDescriptionManager(ByteBuffer buffer, InputManager inputManager) {
        this.buffer = buffer;
        this.inputManager = inputManager;
    }

    @Override
    public void descriptorMessage() {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array(), 0, buffer.limit());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            ServerAnswerBuffer receivedObject = (ServerAnswerBuffer) objectInputStream.readObject();

            if (receivedObject.getAnswerStatus() == AnswerStatus.EXIT) {
                System.exit(0);
            }

            if (receivedObject.getAnswerStatus() == AnswerStatus.FILE) {
                inputManager.setInputStrategy(new FileInput(receivedObject.getComment()));
                return;
            }

            if (receivedObject.getCommandName().equals("help")) {
                System.out.println(receivedObject.getComment());
            }

            System.out.println(receivedObject);

            objectInputStream.close();
            byteArrayInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}