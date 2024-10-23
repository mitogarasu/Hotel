package com.example.hotel;

import java.sql.*;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:hotel.db";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
        }
        return connection;
    }

    public static void createRoomsTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS rooms (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number INTEGER NOT NULL," +
                "area TEXT NOT NULL," +
                "category TEXT NOT NULL," +
                "status TEXT NOT NULL" +
                ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Таблица 'rooms' успешно создана или уже существует.");
        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблицы 'rooms': " + e.getMessage());
            e.printStackTrace();
        }
    }


}
