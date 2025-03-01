package com.example.console_app.api.input_entities.movie.genre;

import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.input_entities.types_builder.EnumBuilder;
import com.example.console_app.entities.enums.MovieGenre;

/**
 * Класс GenreBuilder реализует интерфейс EnumBuilder и предназначен для обработки и хранения значения жанра фильма.
 * Поддерживает значения: ACTION, WESTERN, SCIENCE_FICTION.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class GenreBuilder implements EnumBuilder {
    private MovieGenre movieGenre;

    /**
     * Сбрасывает значение жанра фильма на null.
     */
    @Override
    public void reset() {
        movieGenre = null;
    }

    /**
     * Устанавливает значение жанра фильма на основе строки.
     *
     * @param valueString строка, представляющая жанр фильма.
     * @throws IncorrectInputException если строка не соответствует допустимым значениям.
     */
    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        switch (valueString) {
            case "ACTION":
                movieGenre = MovieGenre.ACTION;
                break;
            case "WESTERN":
                movieGenre = MovieGenre.WESTERN;
                break;
            case "SCIENCE_FICTION":
                movieGenre = MovieGenre.SCIENCE_FICTION;
                break;
            default:
                throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\" не соответствует типу MovieGenre. ");
        }
    }

    /**
     * Возвращает текущее значение жанра фильма.
     *
     * @return значение жанра фильма.
     */
    public MovieGenre getMovieGenre() {
        return movieGenre;
    }
}