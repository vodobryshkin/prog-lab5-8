package com.example.server.modules.interfaces;

import domain.chat.classes.CommandBuffer;

import java.io.IOException;

public interface ServerDescriptor {
    CommandBuffer decryptMessage() throws IOException, ClassNotFoundException;
}
