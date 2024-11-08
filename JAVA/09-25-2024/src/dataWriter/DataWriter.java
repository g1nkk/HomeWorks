package dataWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;

import static databaseConnection.DatabaseProperties.*;

public class DataWriter {


    private static final String[] MESSAGES = {
            "Новое сообщение от ",
            "Произошла ошибка в "
    };

    private static final String[] TYPES = {
            "INFO",
            "WARN"
    };

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Random random = new Random();
            while (true) {
                String message = MESSAGES[random.nextInt(2)] + LocalDateTime.now();
                String type = TYPES[random.nextInt(2)];

                String query = "INSERT INTO notice (message, type, processed) VALUES (?, ?, false)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, message);
                    preparedStatement.setString(2, type);
                    preparedStatement.executeUpdate();
                }
                Thread.sleep(1000); // Sleep на 1000мс
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}