package com.example.console_app.api.input_entities.movie.operator;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.input_entities.movie.operator.eye_color.EyeColorBuilderDirector;
import com.example.console_app.api.input_entities.movie.operator.hair_color.HairColorBuilderDirector;
import com.example.console_app.api.input_entities.movie.operator.height.HeightBuilderDirector;
import com.example.console_app.api.input_entities.movie.operator.location.LocationBuilderDirector;
import com.example.console_app.api.input_entities.movie.operator.name.NameBuilderDirector;
import com.example.console_app.api.input_entities.movie.operator.nationality.NationalityBuilderDirector;
import com.example.console_app.api.input_entities.types_builder.PersonBuilder;
import com.example.console_app.entities.classes.Person;

public class OperatorBuilder implements PersonBuilder {
    private Person operator;
    private InputManager inputManager;
    private NameBuilderDirector nameBuilderDirector;
    private HeightBuilderDirector heightBuilderDirector;
    private EyeColorBuilderDirector eyeColorBuilderDirector;
    private HairColorBuilderDirector hairColorBuilderDirector;
    private NationalityBuilderDirector nationalityBuilderDirector;
    private LocationBuilderDirector locationBuilderDirector;

    public OperatorBuilder(InputManager inputManager) {
        this.inputManager = inputManager;
        operator = new Person();

        nameBuilderDirector = new NameBuilderDirector(this.inputManager);
        heightBuilderDirector = new HeightBuilderDirector(this.inputManager);
        eyeColorBuilderDirector = new EyeColorBuilderDirector(this.inputManager);
        hairColorBuilderDirector = new HairColorBuilderDirector(this.inputManager);
        nationalityBuilderDirector = new NationalityBuilderDirector(this.inputManager);
        locationBuilderDirector = new LocationBuilderDirector(this.inputManager);
    }

    @Override
    public void reset() {
        operator = new Person();
    }

    @Override
    public void inputName() throws IncorrectInputException {
        operator.setName(nameBuilderDirector.getName());
    }

    @Override
    public void inputHeight() throws IncorrectInputException {
        operator.setHeight(heightBuilderDirector.getHeight());
    }

    @Override
    public void inputEyeColor() throws IncorrectInputException {
        operator.setEyeColor(eyeColorBuilderDirector.getEyeColor());
    }

    @Override
    public void inputHairColor() throws IncorrectInputException {
        operator.setHairColor(hairColorBuilderDirector.getHairColor());
    }

    @Override
    public void inputNationality() throws IncorrectInputException {
        operator.setNationality(nationalityBuilderDirector.getNationality());
    }

    @Override
    public void inputLocation() throws IncorrectInputException {
        operator.setLocation(locationBuilderDirector.getLocation());
    }

    public Person getOperator() {
        return operator;
    }
}
