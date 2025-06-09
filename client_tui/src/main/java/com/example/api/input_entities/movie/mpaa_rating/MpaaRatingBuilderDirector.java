package com.example.api.input_entities.movie.mpaa_rating;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input.classes.input_strategies.KeyboardInput;
import com.example.api.input_entities.exceptions.IncorrectInputException;
import entities.enums.MpaaRating;

/**
 * Класс MpaaRatingBuilderDirector отвечает за управление процессом 
 * получения значения рейтинга MPAA через InputManager.
 * 
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-19-02
 */
public class MpaaRatingBuilderDirector {
    private InputManager inputManager;
    private MpaaRatingBuilder mpaaRatingBuilder;

    /**
     * Конструктор класса MpaaRatingBuilderDirector.
     *
     * @param inputManager объект, отвечающий за управление вводом данных.
     */
    public MpaaRatingBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        mpaaRatingBuilder = new MpaaRatingBuilder();
    }

    /**
     * Получает значение рейтинга MPAA, запрашивая ввод у пользователя.
     *
     * @return значение рейтинга MPAA.
     * @throws IncorrectInputException если ввод некорректен.
     */
    public MpaaRating getMpaaRating() throws IncorrectInputException {
        mpaaRatingBuilder.reset();

        try {
            if (inputManager.toString() != "FileInput") {
                System.out.println("Введите поле mpaaRating (доступные варианты: G, PG, PG_13, R, NC_17).");
            }
            mpaaRatingBuilder.setValue(inputManager.readNext());
            return mpaaRatingBuilder.getMpaaRating();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return null;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getMpaaRating();
            }
        }
    }
}
