package com.example.console_app.api.input_entities.movie.operator;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input_entities.ask.AskedResponseDirector;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.entities.classes.Person;

public class OperatorBuilderDirector {
    private InputManager inputManager;
    private OperatorBuilder operatorBuilder;
    
    public OperatorBuilderDirector(InputManager inputManager) {
        this.inputManager = inputManager;
        operatorBuilder = new OperatorBuilder(this.inputManager);
    }
    
    public Person getOperator() throws IncorrectInputException {
        operatorBuilder.reset();

        String typeNameString = inputManager.toString();
        operatorBuilder.inputName();
        if (!typeNameString.equals(inputManager.toString())) {
            return null;
        }

        String typeHeightString = inputManager.toString();
        operatorBuilder.inputHeight();
        if (!typeHeightString.equals(inputManager.toString())) {
            return null;
        }

        String typeEyeColorString = inputManager.toString();
        AskedResponseDirector askedResponseDirector = new AskedResponseDirector("Поле eyeColor.\n", inputManager);
        boolean responseEyeColor = askedResponseDirector.getResponse();

        if (!responseEyeColor && !typeEyeColorString.equals(inputManager.toString())) {
            return null;
        }
        if (responseEyeColor) {
            operatorBuilder.inputEyeColor();
            if (!typeEyeColorString.equals(inputManager.toString())) {
                return null;
            }
        }
        

        String typeHairColorString = inputManager.toString();
        operatorBuilder.inputHairColor();
        if (!typeHairColorString.equals(inputManager.toString())) {
            return null;
        }
        
        String typeNationalityString = inputManager.toString();
        askedResponseDirector = new AskedResponseDirector("Поле nationality.\n", inputManager);
        boolean responseNationality = askedResponseDirector.getResponse();

        if (!responseNationality && !typeNationalityString.equals(inputManager.toString())) {
            return null;
        }
        if (responseNationality) {
            operatorBuilder.inputNationality();
            if (!typeNationalityString.equals(inputManager.toString())) {
                return null;
            }
        }

        String typeLocationString = inputManager.toString();
        askedResponseDirector = new AskedResponseDirector("Поле location.\n", inputManager);
        boolean responseLocation = askedResponseDirector.getResponse();

        if (!responseLocation && !typeLocationString.equals(inputManager.toString())) {
            return null;
        }
        if (responseLocation) {
            operatorBuilder.inputLocation();
            if (!typeLocationString.equals(inputManager.toString())) {
                return null;
            }
        }

        return operatorBuilder.getOperator();
    }
}