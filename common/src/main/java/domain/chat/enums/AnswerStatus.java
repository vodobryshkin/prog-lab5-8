package domain.chat.enums;

public enum AnswerStatus {
    OK,
    EXIT,
    FILE,
    ERROR;

    @Override
    public String toString() {
        if (name().equals("OK")) {
            return "успешно";
        }
        return "неудачно";
    }
}
