package entities.enums;

import java.io.Serializable;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Перечисление, представляющее список возможных стран.
 */
public enum Country implements Serializable {
    FRANCE,
    SPAIN,
    CHINA,
    VATICAN;

    /**
     * Преобразует строковое представление страны в объект Country.
     *
     * @param countryStr Строковое представление страны (регистр не важен). Если строка равна "null", возвращает null.
     * @return Объект Country, соответствующий строковому представлению.
     * @throws IllegalArgumentException Если строковое представление не соответствует ни одной из констант перечисления.
     */
    public static Country parseCountry(String countryStr) {
        if (countryStr.equals("null")) {
            return null;
        }

        try {
            return Country.valueOf(countryStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown country: " + countryStr, e);
        }
    }
}
