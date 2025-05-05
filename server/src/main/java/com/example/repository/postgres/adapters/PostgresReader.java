package com.example.repository.postgres.adapters;

import com.example.repository.interfaces.Reader;
import entities.classes.Movie;
import java.sql.*;
import java.util.ArrayList;

public class PostgresReader implements Reader {
    private final ArrayList<Movie> collection;
    private final String DB_URL = "jdbc:postgresql://localhost:5433/studs";
    private final String DB_USER = "s465774";
    private final String DB_PASSWORD = "7pmr4QtqCjSDZcwq";

    public PostgresReader() {
        collection = new ArrayList<>();
    }

    @Override
    public ArrayList<Movie> read() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC драйвер не найден!");
            e.printStackTrace();
            return collection;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select\n" +
                     "    m.id as id,\n" +
                     "    m.name as name,\n" +
                     "    u.login as login,\n" +
                     "    c.x as coordinates_x,\n" +
                     "    c.y as coordinates_y,\n" +
                     "    m.creation_date as creationDate,\n" +
                     "    m.oscars_count as oscarsCount,\n" +
                     "    m.genre as genre,\n" +
                     "    m.mpaa_rating as mpaaRating,\n" +
                     "    p.name as operator_name,\n" +
                     "    p.height as operator_height,\n" +
                     "    p.eye_color as operator_eyeColor,\n" +
                     "    p.hair_color as operator_hairColor,\n" +
                     "    p.nationality as operator_nationality,\n" +
                     "    l.x as operator_location_x,\n" +
                     "    l.y as operator_location_y,\n" +
                     "    l.z as operator_location_z\n" +
                     "from\n" +
                     "    movie m\n" +
                     "        join\n" +
                     "    coordinates c on c.id = m.coordinates_id\n" +
                     "        left join\n" +
                     "    person p on m.operator_id = p.id\n" +
                     "        left join\n" +
                     "    location l on p.location_id = l.id" +
                     "        join\n" +
                     "    users u on m.user_id = u.id;")) {

            // Получаем метаданные результата (информацию о колонках)
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            System.out.println();

            // Выводим данные
            while (resultSet.next()) {
                StringBuilder line = new StringBuilder();

                for (int i = 1; i <= columnCount; i++) {
                    line.append(resultSet.getString(i)).append(",");
                }
                collection.add(Movie.parseMovie(line.toString()));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при работе с PostgreSQL:");
            e.printStackTrace();
        }
        System.out.println(collection);
        return collection;
    }
}