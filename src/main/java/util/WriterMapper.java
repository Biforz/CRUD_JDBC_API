package util;

import model.Writer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WriterMapper {
    public static Writer mappingWriter(ResultSet resultSet) throws SQLException {
        Writer writer = new Writer();
        writer.setId(resultSet.getLong(1));
        writer.setFirstName(resultSet.getString(2));
        writer.setLastName(resultSet.getString(3));
        return writer;
    }
}
