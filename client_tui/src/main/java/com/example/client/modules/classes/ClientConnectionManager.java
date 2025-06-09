package com.example.client.modules.classes;

import com.example.client.modules.interfaces.ClientConnectable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientConnectionManager implements ClientConnectable {
    private final String host;
    private final int port;

    public ClientConnectionManager(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public boolean connect(DatagramChannel channel, ByteBuffer buffer) throws IOException {
        channel.connect(new InetSocketAddress(host, port));
        return false;
    }
}
