package config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConfigDataBase {
    private static Connection connectionDB;

    public static Connection connection() {
        if (connectionDB == null) {
            Properties properties = new Properties();
            try (InputStream inputStream = Files.newInputStream(
                    Paths.get("src/main/resources/database.properties"))) {
                properties.load(inputStream);
                String url = properties.getProperty("url");
                String username = properties.getProperty("username");
                String password = properties.getProperty("password");
                String driver = properties.getProperty("driver");

                Class.forName(driver);
                connectionDB = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return connectionDB;
    }
}
