package com.example.client.modules.classes;

import com.example.client.modules.interfaces.ClientReceiver;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientReceiverManager implements ClientReceiver {
    private final DatagramChannel channel;
    private final ByteBuffer buffer;

    public ClientReceiverManager(DatagramChannel channel, ByteBuffer buffer) {
        this.channel = channel;
        this.buffer = buffer;
    }

    @Override
    public boolean receiveMessage() {
        buffer.clear();

        try {
            channel.receive(buffer);
        } catch (IOException e) {
            buffer.flip();
            return false; // Повторная попытка получения сообщения
        }

        buffer.flip();
        return true;
    }
}
