package entities.enums;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Перечисление, представляющее возможные цвета глаз.
 */
public enum EyeColor {
    GREEN,
    BLACK,
    BLUE;

    /**
     * Преобразует строковое представление цвета глаз в объект EyeColor.
     *
     * @param eyeColorStr Строковое представление цвета глаз (регистр не важен).  Если строка равна "null", возвращает null.
     * @return Объект EyeColor, соответствующий строковому представлению.
     * @throws IllegalArgumentException Если строковое представление не соответствует ни одной из констант перечисления.
     */
    public static EyeColor parseEyeColor(String eyeColorStr) {
        if (eyeColorStr.equals("null")) {
            return null;
        }

        try {
            return EyeColor.valueOf(eyeColorStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown eye color: " + eyeColorStr, e);
        }
    }
}
