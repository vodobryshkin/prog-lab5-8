package com.example.client.modules.classes;

import com.example.client.modules.interfaces.ClientDescriptor;

import java.nio.ByteBuffer;

public class ClientDescriptionManager implements ClientDescriptor {
    private final ByteBuffer buffer;

    public ClientDescriptionManager(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void descriptorMessage() {
        String receivedMessage = new String(buffer.array(), 0, buffer.limit());
        System.out.println("Получено от сервера: " + receivedMessage);
    }
}
