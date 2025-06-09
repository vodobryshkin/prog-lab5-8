package com.example.api.input_entities.movie.operator.location.z;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input.classes.input_strategies.KeyboardInput;
import com.example.api.input_entities.exceptions.IncorrectInputException;

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

        if (!inputManager.toString().equals("FileInput")) {
            System.out.println("Введите координату z (поле location).");
        }
        coordinateZBuilder.setValue(inputManager.readNext());
        return coordinateZBuilder.getZ();

    }
}
