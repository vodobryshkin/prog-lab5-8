package com.example.server.modules.interfaces;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;

public interface ServerConnectable {
    void connect(DatagramChannel channel, Selector selector) throws IOException;
}
