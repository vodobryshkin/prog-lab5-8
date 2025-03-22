package com.example.server.modules.interfaces;

import java.io.IOException;
import java.net.InetSocketAddress;

public interface ServerSender {
    void sendMessage(InetSocketAddress address, String message) throws IOException;
}
