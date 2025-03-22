package com.example.server.modules.interfaces;

import java.io.IOException;

public interface ServerKeyAwaiter {
    void awaitKeys() throws IOException;
}
