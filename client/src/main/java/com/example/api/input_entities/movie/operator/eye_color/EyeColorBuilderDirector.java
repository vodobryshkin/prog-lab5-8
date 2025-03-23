package com.example.api.input_entities.movie.operator.eye_color;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input.classes.input_strategies.KeyboardInput;
import com.example.api.input_entities.exceptions.IncorrectInputException;
import entities.enums.EyeColor;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Директор для построения объекта EyeColor с использованием EyeColorBuilder и InputManager.
 */
public class EyeColorBuilderDirector {
    private InputManager inputManager;
    private EyeColorBuilder eyeColorBuilder;

    /**
     * Создает новый EyeColorBuilderDirector с заданным InputManager.
     *
     * @param inputManager Менеджер ввода для получения данных от пользователя.
     */
    public EyeColorBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        eyeColorBuilder = new EyeColorBuilder();
    }

    /**
     * Получает EyeColor от пользователя, используя InputManager и EyeColorBuilder.
     * В случае некорректного ввода, предлагает повторить ввод.
     *
     * @return Объект EyeColor, полученный от пользователя.  Возвращает null, если ввод из файла и произошла ошибка.
     * @throws IncorrectInputException Если ввод не соответствует допустимым значениям EyeColor.
     */
    public EyeColor getEyeColor() throws IncorrectInputException {
        eyeColorBuilder.reset();

        try {
            if (inputManager.toString() != "FileInput") {
                System.out.println("Введите поле eyeColor (доступные варианты: GREEN, BLACK, BLUE).");
            }
            eyeColorBuilder.setValue(inputManager.readNext());
            return eyeColorBuilder.getEyeColor();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return null;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getEyeColor();
            }
        }
    }
}
