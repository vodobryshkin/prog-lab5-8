package com.example.dataworker.classes;

import domain.chat.classes.CommandBuffer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ByteManager {
    public byte[] toByteArray(CommandBuffer message) throws IOException {
        // Сериализация объекта
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(message);
        oos.flush();
        return baos.toByteArray();
    }
}
