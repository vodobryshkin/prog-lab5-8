package com.example.api.input_entities.movie.mpaa_rating;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.types_builder.EnumBuilder;
import entities.enums.MpaaRating;

/**
 * Класс MpaaRatingBuilder реализует интерфейс EnumBuilder и предназначен для обработки и хранения значения рейтинга MPAA.
 * Поддерживает значения: G, PG, PG_13, R, NC_17.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class MpaaRatingBuilder implements EnumBuilder {
    private MpaaRating mpaaRating;

    /**
     * Сбрасывает значение рейтинга MPAA на null.
     */
    @Override
    public void reset() {
        mpaaRating = null;
    }

    /**
     * Устанавливает значение рейтинга MPAA на основе строки.
     *
     * @param valueString строка, представляющая рейтинг MPAA.
     * @throws IncorrectInputException если строка не соответствует допустимым значениям.
     */
    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        switch (valueString) {
            case "G":
                mpaaRating = MpaaRating.G;
                break;
            case "PG":
                mpaaRating = MpaaRating.PG;
                break;
            case "PG_13":
                mpaaRating = MpaaRating.PG_13;
                break;
            case "R":
                mpaaRating = MpaaRating.R;
                break;
            case "NC_17":
                mpaaRating = MpaaRating.NC_17;
                break;
            default:
                throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\" не соответствует типу MpaaRating. ");
        }
    }

    /**
     * Возвращает текущее значение рейтинга MPAA.
     *
     * @return значение рейтинга MPAA.
     */
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }
}