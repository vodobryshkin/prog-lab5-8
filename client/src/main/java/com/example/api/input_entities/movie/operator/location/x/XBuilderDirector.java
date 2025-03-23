package com.example.api.input_entities.movie.operator.location.x;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input.classes.input_strategies.KeyboardInput;
import com.example.api.input_entities.exceptions.IncorrectInputException;

public class XBuilderDirector {
    private InputManager inputManager;
    private XBuilder coordinateXBuilder;
    
    /**
     * Конструктор класса {@class EyeColorInputDirector}.
     * @param inputManager
    */
    public XBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        coordinateXBuilder = new XBuilder();
    }

    /**
     * Организует ввод переменной eyeColor.
     */
    public Long getX() throws IncorrectInputException {
        coordinateXBuilder.reset();

        try {
            if (inputManager.toString() != "FileInput") {
                System.out.println("Введите координату x (поле location).");
            }
            coordinateXBuilder.setValue(inputManager.readNext());
            return coordinateXBuilder.getX();

        } catch (IncorrectInputException e) {
            if (inputManager.toString().equals("FileInput")) {
                System.out.println(e.getMessage() + "Работа скрипта завершена.");
                inputManager.setInputStrategy(new KeyboardInput());

                return null;
            } else {
                System.out.println(e.getMessage() + "Повторите попытку ввода.");
                return getX();
            }
        }
        
    }
}
