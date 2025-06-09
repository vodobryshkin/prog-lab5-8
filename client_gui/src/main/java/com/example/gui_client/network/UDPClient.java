package com.example.gui_client.network;

import domain.chat.classes.CommandBuffer;
import domain.chat.classes.ServerAnswerBuffer;

import java.io.*;
import java.net.*;

public class UDPClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9876;
    private static final int BUFFER_SIZE = 4096; // 64KB buffer

    private DatagramSocket socket;
    private InetAddress serverAddress;

    public UDPClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        serverAddress = InetAddress.getByName(SERVER_HOST);
    }

    /**
     * Отправляет CommandBuffer на сервер и получает ServerAnswerBuffer в ответ
     */
    public ServerAnswerBuffer sendCommand(CommandBuffer commandBuffer) throws IOException, ClassNotFoundException {
        // Сериализация CommandBuffer в байты
        byte[] sendData = serializeObject(commandBuffer);

        // Создание UDP пакета для отправки
        DatagramPacket sendPacket = new DatagramPacket(
                sendData,
                sendData.length,
                serverAddress,
                SERVER_PORT
        );

        // Отправка пакета
        socket.send(sendPacket);
        System.out.println("Отправлен запрос: " + commandBuffer.toString());

        // Подготовка буфера для получения ответа
        byte[] receiveData = new byte[BUFFER_SIZE];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        // Получение ответа от сервера
        socket.receive(receivePacket);

        // Десериализация полученных данных
        ServerAnswerBuffer response = (ServerAnswerBuffer) deserializeObject(receivePacket.getData(), receivePacket.getLength());
        System.out.println("Получен ответ: " + response.toString());

        return response;
    }

    /**
     * Сериализует объект в массив байтов
     */
    private byte[] serializeObject(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        return baos.toByteArray();
    }

    /**
     * Десериализует массив байтов в объект
     */
    private Object deserializeObject(byte[] data, int length) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data, 0, length);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }

    /**
     * Закрывает соединение
     */
    public void close() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}