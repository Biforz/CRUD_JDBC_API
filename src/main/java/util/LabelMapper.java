package util;

import model.Label;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabelMapper {
    public static Label mappingLabel(ResultSet resultSet) throws SQLException {
        return Label.builder()
                .id(resultSet.getLong(1))
                .name(resultSet.getString(2))
                .build();
    }
}
