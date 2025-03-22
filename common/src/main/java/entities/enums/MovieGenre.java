package entities.enums;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Перечисление, представляющее возможные жанры фильмов.
 */
public enum MovieGenre {
    ACTION,
    WESTERN,
    SCIENCE_FICTION;

    /**
     * Преобразует строковое представление жанра фильма в объект MovieGenre.
     *
     * @param genreStr Строковое представление жанра фильма (регистр не важен). Если строка равна "null", возвращает null.
     * @return Объект MovieGenre, соответствующий строковому представлению.
     * @throws IllegalArgumentException Если строковое представление не соответствует ни одной из констант перечисления.
     */
    public static MovieGenre parseGenre(String genreStr) {
        if (genreStr.equals("null")) {
            return null;
        }

        try {
            return MovieGenre.valueOf(genreStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown movie genre: " + genreStr, e);
        }
    }
}
