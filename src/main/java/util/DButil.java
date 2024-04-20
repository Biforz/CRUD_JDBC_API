package util;

import model.Label;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.ConfigDataBase.connection;

public class DButil {
    private static final String FIND_LABEL_ON_POST =
            "SELECT * FROM label " +
                    "LEFT JOIN label_post lp on label.id = lp.label_id " +
                    "WHERE lp.post_id = ?";

    public static List<Label> showListLabelOnPost(Long id) {
        List<Label> labelList = new ArrayList<>();
        Label label;

        try {
            PreparedStatement preparedStatement = connection().prepareStatement(FIND_LABEL_ON_POST);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                label = new Label();
                label.setId(resultSet.getLong(1));
                label.setName(resultSet.getString(2));
                labelList.add(label);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labelList;
    }
}
