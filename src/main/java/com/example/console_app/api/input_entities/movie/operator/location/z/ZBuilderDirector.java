package com.example.console_app.api.input_entities.movie.operator.location.z;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input.classes.input_strategies.KeyboardInput;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;

public class ZBuilderDirector {
    private InputManager inputManager;
    private ZBuilder coordinateZBuilder;
    
    /**
     * Конструктор класса {@class EyeColorInputDirector}.
     * @param inputManager
    */
    public ZBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        coordinateZBuilder = new ZBuilder();
    }

    /**
     * Организует ввод переменной eyeColor.
     */
    
    public long getZ() throws IncorrectInputException {
        coordinateZBuilder.reset();

        try {
            if (inputManager.toString() != "FileInput") {
                System.out.println("Введите координату z (поле location).");
            }
            coordinateZBuilder.setValue(inputManager.readNext());
            return coordinateZBuilder.getZ();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return 404;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getZ();
            }
        }
        
    }
}
