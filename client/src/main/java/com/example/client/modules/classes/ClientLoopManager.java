package com.example.client.modules.classes;

import com.example.client.modules.enums.Status;
import com.example.client.modules.interfaces.ClientLoop;
import com.example.dataworker.classes.ByteManager;
import domain.chat.classes.CommandBuffer;

import java.io.IOException;

public class ClientLoopManager implements ClientLoop {
    private final ClientSenderManager senderManager;
    private final ClientReceiverManager receiverManager;
    private final ClientDescriptionManager descriptionManager;
    private final ByteManager byteManager;

    public ClientLoopManager(ClientSenderManager senderManager, ClientReceiverManager receiverManager, ClientDescriptionManager descriptionManager) {
        this.senderManager = senderManager;
        this.receiverManager = receiverManager;
        this.descriptionManager = descriptionManager;

        this.byteManager = new ByteManager();
    }

    @Override
    public Status ask(CommandBuffer message) throws IOException {
        byte[] serializedMessage = byteManager.toByteArray(message);
        senderManager.sendMessage(serializedMessage);
        boolean answer_accepted = receiverManager.receiveMessage();

        if (answer_accepted) {
            descriptionManager.descriptorMessage();
            return Status.ACCEPTED;
        } else {
            return Status.REJECTED_RECEIVE;
        }
    }
}
