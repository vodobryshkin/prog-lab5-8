package com.example.console_app.api.input_entities.movie.operator.hair_color;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input.classes.input_strategies.KeyboardInput;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.entities.enums.HairColor;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Директор для построения объекта HairColor с использованием HairColorBuilder и InputManager.
 */
public class HairColorBuilderDirector {
    private InputManager inputManager;
    private HairColorBuilder hairColorBuilder;

    /**
     * Создает новый HairColorBuilderDirector с заданным InputManager.
     *
     * @param inputManager Менеджер ввода для получения данных от пользователя.
     */
    public HairColorBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        hairColorBuilder = new HairColorBuilder();
    }

    /**
     * Получает HairColor от пользователя, используя InputManager и HairColorBuilder.
     * В случае некорректного ввода, предлагает повторить ввод.
     *
     * @return Объект HairColor, полученный от пользователя.  Возвращает null, если ввод из файла и произошла ошибка.
     * @throws IncorrectInputException Если ввод не соответствует допустимым значениям HairColor.
     */
    public HairColor getHairColor() throws IncorrectInputException {
        hairColorBuilder.reset();

        try {
            if (inputManager.toString() != "FileInput") {
                System.out.println("Введите поле hairColor (доступные варианты: GREEN, RED, ORANGE, WHITE, BROWN).");
            }
            hairColorBuilder.setValue(inputManager.readNext());
            return hairColorBuilder.getHairColor();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return null;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getHairColor();
            }
        }
    }
}
