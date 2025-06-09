package com.example.api.input_entities.movie.operator.location;

import com.example.api.input.classes.input_manager.InputManager;
import com.example.api.input_entities.exceptions.IncorrectInputException;
import com.example.api.input_entities.movie.operator.location.x.XBuilderDirector;
import com.example.api.input_entities.movie.operator.location.y.YBuilderDirector;
import com.example.api.input_entities.movie.operator.location.z.ZBuilderDirector;
import com.example.api.input_entities.types_builder.ThreeDimVectorBuilder;
import entities.classes.Location;

/**
 * Строитель (Builder) для создания объекта Location (трехмерный вектор).
 * Реализует интерфейс ThreeDimVectorBuilder.
 */
public class LocationBuilder implements ThreeDimVectorBuilder {
    private Location location;
    private InputManager inputManager;
    private XBuilderDirector xBuilderDirector;
    private YBuilderDirector yBuilderDirector;
    private ZBuilderDirector zBuilderDirector;
    
    /**
     * Конструктор класса LocationBuilder.
     *
     * @param inputManager Менеджер ввода, используемый для получения данных от пользователя.
     */
    public LocationBuilder(InputManager inputManager) {
        this.inputManager = inputManager;

        location = new Location();
        xBuilderDirector = new XBuilderDirector(this.inputManager);
        yBuilderDirector = new YBuilderDirector(this.inputManager);
        zBuilderDirector = new ZBuilderDirector(this.inputManager);
    }

    /**
     * Сбрасывает объект Location к новому экземпляру.
     */
    @Override
    public void reset() {
        location = new Location();
    }

    /**
     * Получает и устанавливает координату X для Location, используя XBuilderDirector.
     *
     * @throws IncorrectInputException Если введенное значение координаты X некорректно.
     */
    @Override
    public void inputX() throws IncorrectInputException {
        location.setX(xBuilderDirector.getX());
    }

    /**
     * Получает и устанавливает координату Y для Location, используя YBuilderDirector.
     *
     * @throws IncorrectInputException Если введенное значение координаты Y некорректно.
     */
    @Override
    public void inputY() throws IncorrectInputException {
        location.setY(yBuilderDirector.getY());
    }

    /**
     * Получает и устанавливает координату Z для Location, используя ZBuilderDirector.
     *
     */
    @Override
    public void inputZ() throws IncorrectInputException {
        location.setZ(zBuilderDirector.getZ());
    }

    /**
     * Возвращает созданный объект Location.
     *
     * @return Созданный объект Location.
     */
    public Location getLocation() {
        return location;
    }
}
