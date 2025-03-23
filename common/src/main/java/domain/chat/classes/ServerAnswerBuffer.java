package domain.chat.classes;

import domain.chat.enums.AnswerStatus;

import java.io.Serializable;

public class ServerAnswerBuffer implements Serializable {
    private final String commandName;
    private final AnswerStatus answerStatus;
    private final String comment;

    public ServerAnswerBuffer(String commandName, AnswerStatus answerStatus, String comment) {
        this.commandName = commandName;
        this.answerStatus = answerStatus;
        this.comment = comment;
    }

    public String getCommandName() {
        return commandName;
    }

    public AnswerStatus getAnswerStatus() {
        return answerStatus;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Команда " + commandName + " выполнилась " + answerStatus + ". " + comment;
    }
}
