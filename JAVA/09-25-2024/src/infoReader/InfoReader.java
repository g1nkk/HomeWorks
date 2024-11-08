package infoReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static databaseConnection.DatabaseProperties.*;


public class InfoReader {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            while (true) {
                String query = "SELECT id, message, type, processed FROM notice WHERE type = 'INFO' AND processed = false";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String message = resultSet.getString("message");

                        System.out.println("INFO message: " + message);

                        // Удаление сообщения после его прочтения
                        String deleteQuery = "DELETE FROM notice WHERE id = ?";
                        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                            deleteStatement.setInt(1, id);
                            deleteStatement.executeUpdate();
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
