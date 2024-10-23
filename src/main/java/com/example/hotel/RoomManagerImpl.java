package com.example.hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomManagerImpl implements RoomManager {

    @Override
    public void addRoom(Room room) {
        String insertSQL = "INSERT INTO rooms(number, area, category, status) VALUES(?, ?, ?, ?)";

        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, room.getNumber());
            pstmt.setString(2, room.getArea());
            pstmt.setString(3, room.getCategory());
            pstmt.setString(4, room.getStatus());
            pstmt.executeUpdate();

            // Получаем сгенерированный id
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                room.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void removeRoom(Room room) {
        String deleteSQL = "DELETE FROM rooms WHERE id = ?";

        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, room.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String selectSQL = "SELECT * FROM rooms";

        try (Connection conn = DatabaseHandler.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int number = rs.getInt("number");
                String area = rs.getString("area");
                String category = rs.getString("category");
                String status = rs.getString("status");

                Room room = new Room(id, number, area, category, status);
                rooms.add(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

}
