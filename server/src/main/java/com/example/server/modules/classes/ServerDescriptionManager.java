package com.example.server.modules.classes;

import com.example.server.modules.interfaces.ServerDescriptor;
import domain.chat.classes.CommandBuffer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class ServerDescriptionManager implements ServerDescriptor {
    private final ByteBuffer buffer;

    public ServerDescriptionManager(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public CommandBuffer decryptMessage() throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array(), 0, buffer.limit());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (CommandBuffer) ois.readObject();
    }
}
