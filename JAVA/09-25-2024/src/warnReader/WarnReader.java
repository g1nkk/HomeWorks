package warnReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static databaseConnection.DatabaseProperties.*;

public class WarnReader {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            while (true) {
                String query = "SELECT id, message, type, processed FROM notice WHERE type = 'WARN' AND processed = false";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String message = resultSet.getString("message");

                        System.out.println("WARN message: " + message);

                        // Обновление processed на true после прочтения
                        String updateQuery = "UPDATE notice SET processed = true WHERE id = ?";
                        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                            updateStatement.setInt(1, id);
                            updateStatement.executeUpdate();
                        }
                    }
                }
                Thread.sleep(1000); // Sleep на 1000мс
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
