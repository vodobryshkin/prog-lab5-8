package com.example.api.input_entities.movie.operator.nationality;

import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.types_builder.EnumBuilder;
import entities.enums.Country;

public class NationalityBuilder implements EnumBuilder{
    Country country;

    @Override
    public void reset() {
        country = null;
    }

    @Override
    public void setValue(String valueString) throws IncorrectInputException {
        switch (valueString) {
            case "FRANCE":
                country = Country.FRANCE;
                break;
            case "SPAIN":
                country = Country.SPAIN;
                break;
            case "CHINA":
                country = Country.CHINA;
                break;
            case "VATICAN":
                country = Country.VATICAN;
                break;
            default:
                throw new IncorrectInputException("Ошибка ввода: " + "\"" + valueString + "\" не соответствует типу Country. ");
        }
    }

    public Country getNationality() {
        return country;
    }
    
}
