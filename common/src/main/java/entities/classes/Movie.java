package entities.classes;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import entities.enums.MovieGenre;
import entities.enums.MpaaRating;
import entities.interfaces.WritableInCsv;
import entities.interfaces.WritableInSql;

/**
 * Класс Movie представляет фильм с различными атрибутами, такими как название, жанр, рейтинг MPAA и другие.
 * Реализует интерфейсы WritableInCsv и Comparable для поддержки записи в CSV и сравнения объектов.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-22-02
 */
public class Movie implements WritableInCsv, WritableInSql, Comparable<Movie>, Serializable {
    /** Статическое поле для генерации уникального ID. */
    public static int nextId = 0;
    private static final String CONFIG_FILE = "common/src/main/resources/id_config.txt";

    private int id; // Значение поля должно быть больше 0, уникальное и генерируется автоматически
    private String name; // Поле не может быть null, строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private LocalDate creationDate; // Поле не может быть null, значение генерируется автоматически
    private Long oscarsCount; // Значение поля должно быть больше 0, может быть null
    private MovieGenre genre; // Поле может быть null
    private MpaaRating mpaaRating; // Поле не может быть null
    private Person operator; // Поле может быть null

    static {
        loadIdFromFile();
    }

    public static void loadIdFromFile() {
        try {
            Path path = Paths.get(CONFIG_FILE);
            if (Files.exists(path)) {
                String content = Files.readString(path).trim();
                nextId = Integer.parseInt(content);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading ID from file: " + e.getMessage());
            // Можно установить значение по умолчанию или оставить текущее
        }
    }

    public static void saveIdToFile() {
        try {
            Path path = Paths.get(CONFIG_FILE);
            Files.writeString(path, String.valueOf(nextId + 1));
        } catch (IOException e) {
            System.err.println("Error saving ID to file: " + e.getMessage());
        }
    }

    /**
     * Конструктор по умолчанию. Инициализирует ID, дату создания и устанавливает необязательные поля в null.
     */
    public Movie() {
        loadIdFromFile();
        this.id = nextId;
        saveIdToFile();
        this.creationDate = LocalDate.now();
        this.genre = null;
        this.oscarsCount = null;
        this.operator = null;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name=" + name +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate +
                ", oscarsCount=" + oscarsCount +
                ", genre=" + genre +
                ", mpaaRating=" + mpaaRating +
                ", operator=" + operator +
                '}';
    }

    @Override
    public String toSql() {
        String personSql = (operator != null) ? operator.toCsv() : null;

        if (personSql == null) {
            return coordinates.toSql() +
                    "insert into movie(name, coordinates_id, creation_date, oscars_count, genre, " +
                    "mpaa_rating) values ('" + name + "', " +
                    "(select id from coordinates where (coordinates.x, coordinates.y) = (" + coordinates.toCsv() + ")), " +
                    "'" + creationDate + "', " + oscarsCount + ", '" + genre + "', '" + mpaaRating + "');\n";
        }

        return operator.toSql() + coordinates.toSql() +
                "insert into movie(name, coordinates_id, creation_date, oscars_count, genre, " +
                "mpaa_rating, operator_id) values ('" + name + "', " +
                "(select id from coordinates where (coordinates.x, coordinates.y) = (" + coordinates.toCsv() + ")), " +
                "'" + creationDate + "', " + oscarsCount + ", '" + genre + "', '" + mpaaRating + "', " +
                "(select id from person where (name, height, eye_color, hair_color, nationality) = " +
                "('" + operator.getName() + "', " + operator.getHeight() + ", '" +
                operator.getEyeColor() + "', '" + operator.getHairColor() + "', '" +
                operator.getNationality() + "')));\n";
    }

    @Override
    public String toCsv() {
        String personCsv = (operator != null) ? operator.toCsv() : null + "," + null + "," + null + "," + null + "," + null + "," + null + "," + null + "," + null;
        return id + "," + name + "," + coordinates.toCsv() + "," + creationDate + "," + oscarsCount + "," + genre + "," + mpaaRating + "," + personCsv;
    }

    /**
     * Возвращает ID фильма.
     *
     * @return ID фильма.
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает название фильма.
     *
     * @return Название фильма.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает координаты фильма.
     *
     * @return Координаты фильма.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Возвращает дату создания фильма.
     *
     * @return Дата создания фильма.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает количество Оскаров.
     *
     * @return Количество Оскаров (может быть null).
     */
    public Long getOscarsCount() {
        return oscarsCount;
    }

    /**
     * Возвращает жанр фильма.
     *
     * @return Жанр фильма (может быть null).
     */
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * Возвращает рейтинг MPAA.
     *
     * @return Рейтинг MPAA.
     */
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    /**
     * Возвращает оператора фильма.
     *
     * @return Оператор фильма (может быть null).
     */
    public Person getOperator() {
        return operator;
    }

    /**
     * Устанавливает название фильма.
     *
     * @param name Название фильма.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает координаты фильма.
     *
     * @param coordinates Координаты фильма.
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Устанавливает количество Оскаров.
     *
     * @param oscarsCount Количество Оскаров.
     */
    public void setOscarsCount(Long oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    /**
     * Устанавливает жанр фильма.
     *
     * @param genre Жанр фильма.
     */
    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    /**
     * Устанавливает рейтинг MPAA.
     *
     * @param mpaaRating Рейтинг MPAA.
     */
    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    /**
     * Устанавливает оператора фильма.
     *
     * @param operator Оператор фильма.
     */
    public void setOperator(Person operator) {
        this.operator = operator;
    }

    /**
     * Устанавливает ID фильма.
     *
     * @param id ID фильма.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Устанавливает дату создания фильма.
     *
     * @param creationDate Дата создания фильма.
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Преобразует строку в объект Movie.
     *
     * @param movieString Строка, содержащая данные фильма.
     * @return Объект Movie.
     */
    public static Movie parseMovie(String movieString) {
        Movie movie = new Movie();
        String[] params = movieString.split(",");

        movie.setId(Integer.parseInt(params[0]));
        nextId = Integer.parseInt(params[0]);
        saveIdToFile();
        movie.setName(params[1]);
        movie.setCoordinates(Coordinates.parseCoordinates(params[2], params[3]));
        movie.setCreationDate(LocalDate.parse(params[4]));
        movie.setOscarsCount(params[5].equals("null") ? null : Long.parseLong(params[5]));
        movie.setGenre(MovieGenre.parseGenre(params[6]));
        movie.setMpaaRating(MpaaRating.parseMpaaRating(params[7]));
        movie.setOperator(Person.parsePerson(params[8], params[9], params[10], params[11], params[12], params[13], params[14], params[15]));

        return movie;
    }

    @Override
    public int compareTo(Movie other) {
        if (this.name == null && other.name == null) {
            return 0;
        }
        if (this.name == null) {
            return -1;
        }
        if (other.name == null) {
            return 1;
        }
        return Integer.compare(this.name.length(), other.getName().length());
    }
}