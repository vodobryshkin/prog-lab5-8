package com.example.server.modules.classes;

import com.example.repository.exceptions.KeyNotFoundException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;

public class ServerChatManager implements Runnable {
    private final Selector selector;
    private final ServerKeyAwaiterManager serverKeyAwaiterManager;


    public ServerChatManager(DatagramChannel channel, ByteBuffer buffer, Selector selector) {
        serverKeyAwaiterManager = new ServerKeyAwaiterManager(channel, buffer, selector);
        this.selector = selector;
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                serverKeyAwaiterManager.awaitKeys();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (KeyNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
