package com.example.server.modules.classes;

import com.example.server.modules.interfaces.ServerConnectable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class ServerConnectionManager implements ServerConnectable {
    private final int port;

    public ServerConnectionManager(int port) {
        this.port = port;
    }

    @Override
    public void connect(DatagramChannel channel, Selector selector) throws IOException {
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress(port));
        channel.register(selector, SelectionKey.OP_READ);
        ServerManager.logger.info("Server started at port {}", port);
    }
}
