package com.example.client.modules.classes;

import com.example.client.modules.interfaces.ClientDescriptor;
import domain.chat.classes.ServerAnswerBuffer;
import domain.chat.enums.AnswerStatus;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class ClientDescriptionManager implements ClientDescriptor {
    private final ByteBuffer buffer;

    public ClientDescriptionManager(ByteBuffer buffer) {
        this.buffer = buffer;
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

            System.out.println(receivedObject);

            objectInputStream.close();
            byteArrayInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}