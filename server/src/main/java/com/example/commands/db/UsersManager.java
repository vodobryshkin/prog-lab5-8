package com.example.commands.db;

import domain.chat.classes.ServerAnswerBuffer;
import domain.chat.enums.AnswerStatus;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class UsersManager {
    private final String DB_URL = "jdbc:postgresql://localhost:5433/studs";
    private final String DB_USER = "s465774";
    private final String DB_PASSWORD = "7pmr4QtqCjSDZcwq";

    private static final int SALT_LENGTH = 16; // 16 bytes = 24 symbols in Base64

    public ServerAnswerBuffer authenticate(String commandName, String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Получаем соль и хеш из базы данных
            String sql = "SELECT salt, hash_password FROM users WHERE login = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String storedSalt = rs.getString("salt");
                    String storedHash = rs.getString("hash_password");

                    // Хешируем введенный пароль с сохраненной солью
                    String inputHash = hashPassword(password, storedSalt);

                    if (inputHash.equals(storedHash)) {
                        return new ServerAnswerBuffer(commandName, AnswerStatus.OK, username);
                    }
                }
            }
            return new ServerAnswerBuffer(commandName, AnswerStatus.ERROR, "Неверное имя пользователя или пароль.");
        } catch (SQLException | NoSuchAlgorithmException e) {
            return new ServerAnswerBuffer(commandName, AnswerStatus.ERROR, "Ошибка аутентификации: " + e.getMessage());
        }
    }

    public ServerAnswerBuffer register(String commandName, String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Генерируем случайную соль
            String salt = generateSalt();
            // Хешируем пароль с солью
            String hashedPassword = hashPassword(password, salt);

            // Сохраняем пользователя в базу данных
            String sql = "INSERT INTO users (login, hash_password, salt) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, hashedPassword);
                stmt.setString(3, salt);
                stmt.executeUpdate();

                return new ServerAnswerBuffer(commandName, AnswerStatus.OK, username);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            return new ServerAnswerBuffer(commandName, AnswerStatus.ERROR, "Ошибка регистрации: " + e.getMessage());
        }
    }

    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-384");
        String combined = password + salt;
        byte[] hash = digest.digest(combined.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
}