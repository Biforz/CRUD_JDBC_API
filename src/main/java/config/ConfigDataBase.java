package config;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return connectionDB;
    }

    public static PreparedStatement preparedStatement(String sql) throws SQLException {
        return connection().prepareStatement(sql);
    }
}
