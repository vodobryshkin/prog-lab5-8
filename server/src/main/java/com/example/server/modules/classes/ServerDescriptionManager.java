package com.example.server.modules.classes;

import com.example.server.modules.interfaces.ServerDescriptor;

import java.nio.ByteBuffer;

public class ServerDescriptionManager implements ServerDescriptor {
    private final ByteBuffer buffer;

    public ServerDescriptionManager(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public String decryptMessage() {
        return new String(buffer.array(), 0, buffer.limit());
    }
}
