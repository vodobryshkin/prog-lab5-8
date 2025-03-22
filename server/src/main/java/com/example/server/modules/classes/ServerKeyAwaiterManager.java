package com.example.server.modules.classes;

import com.example.server.modules.interfaces.ServerKeyAwaiter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class ServerKeyAwaiterManager implements ServerKeyAwaiter {
    private final Selector selector;
    private final ServerSenderManager serverSenderManager;
    private final ServerReceiverManager serverReceiverManager;
    private final ServerDescriptionManager serverDescriptionManager;

    public ServerKeyAwaiterManager(DatagramChannel channel, ByteBuffer buffer, Selector selector) {
        this.selector = selector;

        serverReceiverManager = new ServerReceiverManager(channel, buffer);
        serverSenderManager = new ServerSenderManager(channel, buffer);
        serverDescriptionManager = new ServerDescriptionManager(buffer);
    }

    @Override
    public void awaitKeys() throws IOException {
        Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            keyIterator.remove();

            if (key.isReadable()) {
                InetSocketAddress address = serverReceiverManager.receiveMessage();

                String receivedMessage = serverDescriptionManager.decryptMessage();

                ServerManager.logger.info("Received message from client {}: {}", address, receivedMessage);

                // Отправляем ответ клиенту
                String responseMessage = "Эхо: " + receivedMessage;

                serverSenderManager.sendMessage(address, responseMessage);
            }
        }
    }
}
