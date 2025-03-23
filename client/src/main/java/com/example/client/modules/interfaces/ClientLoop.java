package com.example.client.modules.interfaces;

import com.example.client.modules.enums.Status;
import domain.chat.classes.CommandBuffer;

import java.io.IOException;

public interface ClientLoop {
    Status ask(CommandBuffer message) throws IOException;
}
