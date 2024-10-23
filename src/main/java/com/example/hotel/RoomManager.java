package com.example.hotel;

import java.util.List;

public interface RoomManager {
    void addRoom(Room room);
    void removeRoom(Room room);
    List<Room> getAllRooms();
}
