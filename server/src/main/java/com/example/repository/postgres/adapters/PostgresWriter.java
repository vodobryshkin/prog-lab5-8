package com.example.repository.postgres.adapters;

import com.example.repository.exceptions.KeyNotFoundException;
import com.example.repository.interfaces.Repository;
import com.example.repository.interfaces.Writer;
import entities.classes.Movie;

import java.sql.*;

public class PostgresWriter implements Writer {
    private final String DB_URL = "jdbc:postgresql://localhost:5433/studs";
    private final String DB_USER = "s465774";
    private final String DB_PASSWORD = "7pmr4QtqCjSDZcwq";

    @Override
    public void write(Repository repository) throws KeyNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC драйвер не найден!");
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("drop table if exists movie;\n" +
                     "create table movie (\n" +
                     "                       id serial primary key,\n" +
                     "                       user_id integer references users(id),\n" +
                     "                       name varchar(32) not null check (name != ''),\n" +
                     "                       coordinates_id integer references coordinates(id) not null,\n" +
                     "                       creation_date date not null default current_timestamp,\n" +
                     "                       oscars_count bigint check (oscars_count > 0) default null,\n" +
                     "                       genre movie_genre default null,\n" +
                     "                       mpaa_rating mpaa_rating not null,\n" +
                     "                       operator_id integer references person(id) default null\n" +
                     ");")) {}
        catch (SQLException e) {
        }
        for (int i = 0; i < repository.getSize(); i++) {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(repository.getByIndex(i).toSql())) {}
            catch (SQLException e) {
                System.err.println("Ошибка при работе с PostgreSQL:");
                e.printStackTrace();
            }
        }

    }
}
