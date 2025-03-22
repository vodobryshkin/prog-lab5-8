package com.example.client.modules.classes;

import com.example.client.modules.enums.Status;
import com.example.client.modules.interfaces.ClientLoop;

public class ClientLoopManager implements ClientLoop {
    private final ClientSenderManager senderManager;
    private final ClientReceiverManager receiverManager;
    private final ClientDescriptionManager descriptionManager;

    public ClientLoopManager(ClientSenderManager senderManager, ClientReceiverManager receiverManager, ClientDescriptionManager descriptionManager) {
        this.senderManager = senderManager;
        this.receiverManager = receiverManager;
        this.descriptionManager = descriptionManager;
    }

    @Override
    public Status ask(String message) {
        senderManager.sendMessage(message);
        boolean answer_accepted = receiverManager.receiveMessage();

        if (answer_accepted) {
            descriptionManager.descriptorMessage();
            return Status.ACCEPTED;
        } else {
            return Status.REJECTED_RECEIVE;
        }
    }
}
