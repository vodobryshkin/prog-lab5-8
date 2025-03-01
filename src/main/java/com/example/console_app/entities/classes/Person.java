package com.example.console_app.entities.classes;

import com.example.console_app.entities.enums.Country;
import com.example.console_app.entities.enums.EyeColor;
import com.example.console_app.entities.enums.HairColor;
import com.example.console_app.entities.interfaces.WritableInCsv;

/**
 * Класс Person представляет человека с такими атрибутами, как имя, рост, цвет глаз, цвет волос, национальность и местоположение.
 * Реализует интерфейс WritableInCsv для поддержки записи данных в CSV-формат.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class Person implements WritableInCsv {
    private String name; // Поле не может быть null, строка не может быть пустой
    private int height; // Значение поля должно быть больше 0
    private EyeColor eyeColor; // Поле может быть null
    private HairColor hairColor; // Поле не может быть null
    private Country nationality; // Поле может быть null
    private Location location; // Поле может быть null

    /**
     * Конструктор по умолчанию. Инициализирует необязательные поля как null.
     */
    public Person() {
        eyeColor = null;
        nationality = null;
        location = null;
    }

    /**
     * Возвращает имя человека.
     *
     * @return Имя человека.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает рост человека.
     *
     * @return Рост человека.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Возвращает цвет глаз человека.
     *
     * @return Цвет глаз (может быть null).
     */
    public EyeColor getEyeColor() {
        return eyeColor;
    }

    /**
     * Возвращает цвет волос человека.
     *
     * @return Цвет волос.
     */
    public HairColor getHairColor() {
        return hairColor;
    }

    /**
     * Возвращает национальность человека.
     *
     * @return Национальность (может быть null).
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * Возвращает местоположение человека.
     *
     * @return Местоположение (может быть null).
     */
    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Person{" +
               "name=" + name +
               ", height='" + height +
               ", eyeColor=" + eyeColor +
               ", hairColor=" + hairColor +
               ", nationality=" + nationality +
               ", location=" + (location != null ? location.toString() : null) +
               '}';
    }

    @Override
    public String toCsv() {
        String locationString = (location != null) ? location.toCsv() : null + "," + null + "," + null;
        return name + "," + height + "," + eyeColor + "," + hairColor + "," + nationality + "," + locationString;
    }

    /**
     * Устанавливает имя человека.
     *
     * @param name Имя человека.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает рост человека.
     *
     * @param height Рост человека.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Устанавливает цвет глаз человека.
     *
     * @param eyeColor Цвет глаз.
     */
    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Устанавливает цвет волос человека.
     *
     * @param hairColor Цвет волос.
     */
    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Устанавливает национальность человека.
     *
     * @param nationality Национальность.
     */
    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    /**
     * Устанавливает местоположение человека.
     *
     * @param location Местоположение.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Преобразует строку в объект Person.
     *
     * @param nameString Имя.
     * @param heightString Рост.
     * @param eyeColorString Цвет глаз.
     * @param hairColorString Цвет волос.
     * @param nationalityString Национальность.
     * @param location1String Координата X местоположения.
     * @param location2String Координата Y местоположения.
     * @param location3String Название местоположения.
     * @return Объект Person.
     */
    public static Person parsePerson(String nameString, String heightString, String eyeColorString, String hairColorString, String nationalityString, String location1String, String location2String, String location3String) {
        if (nameString.equals("null") || heightString.equals("null")) {
            return null;
        }

        Person person = new Person();
        person.setName(nameString);
        person.setHeight(Integer.parseInt(heightString));
        person.setEyeColor(EyeColor.parseEyeColor(eyeColorString));
        person.setHairColor(HairColor.parseHairColor(hairColorString));
        person.setNationality(Country.parseCountry(nationalityString));
        person.setLocation(Location.parseLocation(location1String, location2String, location3String));
        return person;
    }
}