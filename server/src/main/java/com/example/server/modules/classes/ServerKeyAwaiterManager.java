package com.example.server.modules.classes;

import com.example.commands.logic.CollectionManager;
import com.example.commands.logic.CommandsManager;
import com.example.commands.logic.Invoker;
import com.example.repository.postgres.PostgresRepository;
import com.example.repository.exceptions.KeyNotFoundException;
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
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerKeyAwaiterManager implements ServerKeyAwaiter {
    private final Selector selector;
    private final ServerSenderManager serverSenderManager;
    private final ServerReceiverManager serverReceiverManager;
    private final ServerDescriptionManager serverDescriptionManager;
    private final CommandsManager commandsManager;
    private final Invoker invoker;
    private final CollectionManager collectionManager;
    private final ForkJoinPool requestProcessingPool;
    private final ExecutorResponseSender responseSenderExecutor;

    public ServerKeyAwaiterManager(DatagramChannel channel, ByteBuffer buffer, Selector selector,
                                   ForkJoinPool requestProcessingPool) throws SQLException {
        this.selector = selector;
        this.requestProcessingPool = requestProcessingPool;
        this.responseSenderExecutor = new ExecutorResponseSender();

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

                // Обработка запроса в отдельном потоке ForkJoinPool
                requestProcessingPool.execute(() -> {
                    try {
                        ServerAnswerBuffer serverAnswerBuffer = invoker.getAnswer(receivedMessage);

                        // Отправка ответа через CachedThreadPool
                        responseSenderExecutor.submitTask(() -> {
                            try {
                                serverSenderManager.sendMessage(address, serverAnswerBuffer);
                            } catch (IOException e) {
                                ServerManager.logger.error("Error sending response: {}", e.getMessage());
                            }
                        });
                    } catch (Exception e) {
                        ServerManager.logger.error("Error processing request: {}", e.getMessage());
                    }
                });
            }
        }
    }

    // Внутренний класс для управления отправкой ответов
    private static class ExecutorResponseSender {
        private final ExecutorService executor = Executors.newCachedThreadPool();

        void submitTask(Runnable task) {
            executor.submit(task);
        }
    }
}