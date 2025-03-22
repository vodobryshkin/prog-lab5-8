package entities.enums;

import java.io.Serializable;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Перечисление, представляющее возможные цвета волос.
 */
public enum HairColor implements Serializable {
    GREEN,
    RED,
    ORANGE,
    WHITE,
    BROWN;

    /**
     * Преобразует строковое представление цвета волос в объект HairColor.
     *
     * @param hairColorStr Строковое представление цвета волос (регистр не важен).
     * @return Объект HairColor, соответствующий строковому представлению.
     * @throws IllegalArgumentException Если строковое представление не соответствует ни одной из констант перечисления.
     */
    public static HairColor parseHairColor(String hairColorStr) {
        try {
            return HairColor.valueOf(hairColorStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown hair color: " + hairColorStr, e);
        }
    }
}
