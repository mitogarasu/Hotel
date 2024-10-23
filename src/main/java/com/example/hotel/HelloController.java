package com.example.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class HelloController {

    @FXML
    private TextField numberField;

    @FXML
    private TextField areaField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private TableView<Room> roomTableView;

    @FXML
    private TableColumn<Room, Integer> numberColumn;

    @FXML
    private TableColumn<Room, String> areaColumn;

    @FXML
    private TableColumn<Room, String> categoryColumn;

    @FXML
    private TableColumn<Room, String> statusColumn;

    private RoomManager roomManager;

    private ObservableList<Room> roomList;

    @FXML
    private void initialize() {
        // Создаем таблицу, если ее нет
        DatabaseHandler.createRoomsTable();

        // Инициализируем RoomManager
        roomManager = new RoomManagerImpl();

        // Инициализируем ComboBox-ы
        categoryComboBox.setItems(FXCollections.observableArrayList("люкс", "стандарт", "семейный"));
        statusComboBox.setItems(FXCollections.observableArrayList("свободен", "забронирован"));

        // Настраиваем колонки таблицы
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());
        areaColumn.setCellValueFactory(cellData -> cellData.getValue().areaProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        // Инициализируем список номеров и связываем с таблицей
        roomList = FXCollections.observableArrayList();

        // Загружаем комнаты из базы данных
        loadRoomsFromDatabase();

        // Связываем список с таблицей
        roomTableView.setItems(roomList);
    }


    @FXML
    private void handleAddRoom() {
        try {
            int number = Integer.parseInt(numberField.getText());
            String area = areaField.getText();
            String category = categoryComboBox.getValue();
            String status = statusComboBox.getValue();

            if (category == null || status == null) {
                showAlert("Ошибка ввода", "Пожалуйста, выберите категорию и статус.");
                return;
            }

            if (area.isEmpty()) {
                showAlert("Ошибка ввода", "Пожалуйста, введите площадь.");
                return;
            }

            Room room = new Room(number, area, category, status);
            roomManager.addRoom(room);
            roomList.add(room); // Добавляем комнату в список для отображения

            // Очищаем поля ввода
            numberField.clear();
            areaField.clear();
            categoryComboBox.setValue(null);
            statusComboBox.setValue(null);

        } catch (NumberFormatException e) {
            showAlert("Ошибка ввода", "Пожалуйста, введите корректные значения для номера.");
        }
    }


    @FXML
    private void handleDeleteRoom() {
        Room selectedRoom = roomTableView.getSelectionModel().getSelectedItem();
        if (selectedRoom != null) {
            roomManager.removeRoom(selectedRoom);
            roomList.remove(selectedRoom);
        } else {
            showAlert("Ошибка", "Пожалуйста, выберите номер для удаления.");
        }
    }


    private void loadRoomsFromDatabase() {
        List<Room> rooms = roomManager.getAllRooms();
        if (rooms != null && !rooms.isEmpty()) {
            roomList.addAll(rooms);
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
