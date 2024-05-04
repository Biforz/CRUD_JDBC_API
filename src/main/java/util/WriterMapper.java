package util;

import model.Writer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WriterMapper {
    public static Writer mappingWriter(ResultSet resultSet) throws SQLException {
        return Writer.builder()
                .id(resultSet.getLong(1))
                .firstName(resultSet.getString(2))
                .lastName(resultSet.getString(3))
                .build();
    }
}
