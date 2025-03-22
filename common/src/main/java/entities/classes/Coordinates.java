package entities.classes;

import entities.interfaces.WritableInCsv;

import java.io.Serializable;

/**
 *  @author Добрышкин Владимир (vodobryshkin)
 *  @version 1.0
 *  @since 2025-22-02
 * Класс, представляющий координаты объекта в двумерном пространстве.
 */
public class Coordinates implements WritableInCsv, Serializable {
    private long x; // Максимальное значение поля: 611
    private long y;

    /**
     * Проверяет, что значение координаты X не превышает максимальное допустимое значение (611).
     *
     * @return true, если координата X находится в допустимом диапазоне, false в противном случае.
     */
    public boolean check() {
        return x <= 611;
    }

    /**
     * Возвращает строковое представление объекта Coordinates.
     *
     * @return Строка, содержащая значения координат X и Y.
     */
    @Override
    public String toString() {
        return "Coordinates{" +
           "x=" + x +
           ", y=" + y +
           '}';
    }

    /**
     * Преобразует объект Coordinates в строковое представление в формате CSV.
     *
     * @return Строка, содержащая значения координат X и Y, разделенные запятой.
     */
    @Override
    public String toCsv() {
        return x + "," + y;
    }

    /**
     * Возвращает значение координаты X.
     *
     * @return Значение координаты X.
     */
    public long getX() {
        return x;
    }

    /**
     * Возвращает значение координаты Y.
     *
     * @return Значение координаты Y.
     */
    public long getY() {
        return y;
    }

    /**
     * Устанавливает значение координаты X.
     *
     * @param x Новое значение координаты X.
     */
    public void setX(long x) {
        this.x = x;
    }

    /**
     * Устанавливает значение координаты Y.
     *
     * @param y Новое значение координаты Y.
     */
    public void setY(long y) {
        this.y = y;
    }

    /**
     * Создает новый объект Coordinates из строковых представлений координат X и Y.
     *
     * @param xString Строковое представление координаты X.
     * @param yString Строковое представление координаты Y.
     * @return Новый объект Coordinates с заданными значениями координат.
     */
    public static Coordinates parseCoordinates(String xString, String yString) {
        Coordinates coordinates = new Coordinates();
        coordinates.setX(Long.parseLong(xString));
        coordinates.setY(Long.parseLong(yString));
        return coordinates;
    }
}
