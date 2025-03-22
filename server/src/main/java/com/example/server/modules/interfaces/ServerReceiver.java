package com.example.server.modules.interfaces;

import java.io.IOException;
import java.net.InetSocketAddress;

public interface ServerReceiver {
    InetSocketAddress receiveMessage() throws IOException;
}
