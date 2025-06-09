package com.example.client.modules.interfaces;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public interface ClientConnectable {
    boolean connect(DatagramChannel channel, ByteBuffer buffer) throws IOException;
}
