package com.example.server.modules.classes;

import com.example.server.modules.interfaces.ServerSender;
import domain.chat.classes.ServerAnswerBuffer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerSenderManager implements ServerSender {
    private final DatagramChannel channel;
    private final ByteBuffer buffer;

    public ServerSenderManager(DatagramChannel channel, ByteBuffer buffer) {
        this.channel = channel;
        this.buffer = buffer;
    }

    @Override
    public void sendMessage(InetSocketAddress address, ServerAnswerBuffer message) throws IOException {
        // Сериализация объекта в массив байтов
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        byte[] serializedMessage = byteArrayOutputStream.toByteArray();

        // Очистка буфера и запись сериализованных данных
        buffer.clear();
        buffer.put(serializedMessage);
        buffer.flip();

        // Отправка данных
        channel.send(buffer, address);

        ServerManager.logger.info("Send response to client {}: {}", address, message);
    }
}