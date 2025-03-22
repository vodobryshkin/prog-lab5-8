package entities.enums;

import java.io.Serializable;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Перечисление, представляющее возможные рейтинги MPAA для фильмов.
 */
public enum MpaaRating implements Serializable {
    G,
    PG,
    PG_13,
    R,
    NC_17;

    /**
     * Преобразует строковое представление рейтинга MPAA в объект MpaaRating.
     *
     * @param mpaaRatingStr Строковое представление рейтинга MPAA (регистр не важен). Если строка равна "null", возвращает null.
     * @return Объект MpaaRating, соответствующий строковому представлению.
     * @throws IllegalArgumentException Если строковое представление не соответствует ни одной из констант перечисления.
     */
    public static MpaaRating parseMpaaRating(String mpaaRatingStr) {
        if (mpaaRatingStr.equals("null")) {
            return null;
        }

        try {
            return MpaaRating.valueOf(mpaaRatingStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown mpaa rating: " + mpaaRatingStr, e);
        }
    }
}
