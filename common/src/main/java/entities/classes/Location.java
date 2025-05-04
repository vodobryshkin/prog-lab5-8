package entities.classes;

import entities.interfaces.WritableInCsv;
import entities.interfaces.WritableInSql;

import java.io.Serializable;

/**
 •  @author Добрышкин Владимир (vodobryshkin)
 •  @version 1.0
 •  @since 2025-22-02
 • Класс, представляющий местоположение с координатами X, Y и Z.
 */
public class Location implements WritableInCsv, WritableInSql, Serializable {
    private Long x; // Поле не может быть null
    private Double y; // Поле не может быть null
    private long z;

    /**
     * Возвращает строковое представление объекта Location.
     *
     * @return Строка, содержащая значения координат X, Y и Z.
     */
    @Override
    public String toString() {
        return "Location{" +
           "x=" + x +
           ", y=" + y +
           ", z=" + z +
           '}';
    }

    @Override
    public String toSql() {
        return "insert into location(x, y, z) values(" + x + ", " + y + ", " + z + ") on conflict on constraint lu do nothing;\n";
    }

    /**
     * Преобразует объект Location в строковое представление в формате CSV.
     *
     * @return Строка, содержащая значения координат X, Y и Z, разделенные запятыми.
     */
    @Override
    public String toCsv() {
        return x + "," + y + "," + z ;
    }

    /**
     * Возвращает значение координаты X.
     *
     * @return Значение координаты X.
     */
    public Long getX() {
        return x;
    }

    /**
     * Возвращает значение координаты Y.
     *
     * @return Значение координаты Y.
     */
    public Double getY() {
        return y;
    }

    /**
     * Возвращает значение координаты Z.
     *
     * @return Значение координаты Z.
     */
    public long getZ() {
        return z;
    }

    /**
     * Устанавливает значение координаты X.
     *
     * @param x Новое значение координаты X.
     */
    public void setX(Long x) {
        this.x = x;
    }

    /**
     * Устанавливает значение координаты Y.
     *
     * @param y Новое значение координаты Y.
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * Устанавливает значение координаты Z.
     *
     * @param z Новое значение координаты Z.
     */
    public void setZ(long z) {
        this.z = z;
    }

    /**
     * Создает новый объект Location из строковых представлений координат X, Y и Z.
     *
     * @param location1String Строковое представление координаты X.
     * @param location2String Строковое представление координаты Y.
     * @param location3String Строковое представление координаты Z.
     * @return Новый объект Location с заданными значениями координат.
     */
    public static Location parseLocation(String location1String, String location2String, String location3String) {
        Location location = new Location();
        location.setX(Long.parseLong(location1String));
        location.setY(Double.parseDouble(location2String));
        location.setZ(Long.parseLong(location3String));
        
        return location;
    }

}
