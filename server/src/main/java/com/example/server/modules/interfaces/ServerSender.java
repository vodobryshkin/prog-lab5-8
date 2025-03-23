package com.example.server.modules.interfaces;

import domain.chat.classes.ServerAnswerBuffer;

import java.io.IOException;
import java.net.InetSocketAddress;

public interface ServerSender {
    void sendMessage(InetSocketAddress address, ServerAnswerBuffer message) throws IOException;
}
