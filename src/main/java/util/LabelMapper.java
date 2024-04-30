package util;


import model.Label;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabelMapper {
    public static Label mappingLabel(ResultSet resultSet) throws SQLException {
        Label label = new Label();
        label.setId(resultSet.getLong(1));
        label.setName(resultSet.getString(2));
        return label;
    }
}
