package com.example.server.modules.interfaces;

import com.example.repository.exceptions.KeyNotFoundException;

import java.io.IOException;

public interface ServerKeyAwaiter {
    void awaitKeys() throws IOException, ClassNotFoundException, KeyNotFoundException;
}
