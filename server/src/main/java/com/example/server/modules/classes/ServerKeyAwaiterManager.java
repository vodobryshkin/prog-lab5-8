package com.example.server.modules.classes;

import com.example.commands.logic.CollectionManager;
import com.example.commands.logic.CommandsManager;
import com.example.commands.logic.Invoker;
import com.example.repository.csv.CsvRepository;
import com.example.repository.exceptions.KeyNotFoundException;
import com.example.repository.postgres.PostgresRepository;
import com.example.server.modules.interfaces.ServerKeyAwaiter;
import domain.chat.classes.CommandBuffer;
import domain.chat.classes.ServerAnswerBuffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.sql.SQLException;
import java.util.Iterator;

public class ServerKeyAwaiterManager implements ServerKeyAwaiter {
    private final Selector selector;
    private final ServerSenderManager serverSenderManager;
    private final ServerReceiverManager serverReceiverManager;
    private final ServerDescriptionManager serverDescriptionManager;

    private final CommandsManager commandsManager;
    private final Invoker invoker;
    private final CollectionManager collectionManager;

    public ServerKeyAwaiterManager(DatagramChannel channel, ByteBuffer buffer, Selector selector) throws SQLException {
        this.selector = selector;

        serverReceiverManager = new ServerReceiverManager(channel, buffer);
        serverSenderManager = new ServerSenderManager(channel, buffer);
        serverDescriptionManager = new ServerDescriptionManager(buffer);

        collectionManager = new CollectionManager(new PostgresRepository());
        commandsManager = new CommandsManager(collectionManager);
        invoker = new Invoker(commandsManager);
    }

    @Override
    public void awaitKeys() throws IOException, ClassNotFoundException, KeyNotFoundException {
        Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            keyIterator.remove();

            if (key.isReadable()) {
                InetSocketAddress address = serverReceiverManager.receiveMessage();

                CommandBuffer receivedMessage = serverDescriptionManager.decryptMessage();

                ServerManager.logger.info("Received message from client {}: {}", address, receivedMessage.toString());

                ServerAnswerBuffer serverAnswerBuffer = invoker.getAnswer(receivedMessage);

                serverSenderManager.sendMessage(address, serverAnswerBuffer);
            }
        }
    }
}
