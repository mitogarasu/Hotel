package com.example.hotel;

import javafx.beans.property.*;

public class Room {
    private IntegerProperty id;
    private IntegerProperty number;
    private StringProperty area;
    private StringProperty category;
    private StringProperty status;

    // Конструктор с параметром id (используется при загрузке из базы данных)
    public Room(int id, int number, String area, String category, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.number = new SimpleIntegerProperty(number);
        this.area = new SimpleStringProperty(area);
        this.category = new SimpleStringProperty(category);
        this.status = new SimpleStringProperty(status);
    }

    // Конструктор без параметра id (используется при создании новой комнаты перед добавлением в базу данных)
    public Room(int number, String area, String category, String status) {
        this(-1, number, area, category, status); // Устанавливаем id как -1, он будет обновлен после вставки в БД
    }

    // Методы доступа и свойства для id
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    // Методы доступа и свойства для number
    public int getNumber() {
        return number.get();
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    // Методы доступа и свойства для area
    public String getArea() {
        return area.get();
    }

    public void setArea(String area) {
        this.area.set(area);
    }

    public StringProperty areaProperty() {
        return area;
    }

    // Методы доступа и свойства для category
    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    // Методы доступа и свойства для status
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }

    // Переопределение метода toString() для удобства отображения информации о комнате
    @Override
    public String toString() {
        return "Room{" +
                "id=" + getId() +
                ", number=" + getNumber() +
                ", area='" + getArea() + '\'' +
                ", category='" + getCategory() + '\'' +
                ", status='" + getStatus() + '\'' +
                '}';
    }
}
