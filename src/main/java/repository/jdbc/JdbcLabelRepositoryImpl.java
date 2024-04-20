package repository.jdbc;

import model.Label;
import repository.LabelRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static config.ConfigDataBase.connection;

public class JdbcLabelRepositoryImpl implements LabelRepository {
    private static final String SHOW_ALL_LABELS = "SELECT * FROM label";
    private static final String SHOW_LABEL_BY_ID = "SELECT * FROM label WHERE id = ?";
    private static final String ADD_LABEL = "INSERT INTO label(name) VALUES (?)";
    private static final String UPDATE_LABEL = "UPDATE label SET name = ? WHERE id = ?";
    private static final String DELETE_LABEL_BY_ID = "DELETE FROM label WHERE id = ?";

    public List<Label> showAll() {
        List<Label> labelList = new ArrayList<>();
        Label label;
        try (PreparedStatement preparedStatement = connection().prepareStatement(SHOW_ALL_LABELS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                label = new Label();
                label.setId(resultSet.getLong(1));
                label.setName(resultSet.getString(2));
                labelList.add(label);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return labelList;
    }

    public Label showById(Long id) {
        Label label = null;
        try (PreparedStatement preparedStatement = connection().prepareStatement(SHOW_LABEL_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            label = new Label();
            label.setId(resultSet.getLong("id"));
            label.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return label;
    }

    public Label add(Label label) {
        try (PreparedStatement preparedStatement = connection().prepareStatement(ADD_LABEL)) {
            preparedStatement.setString(1, label.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return label;
    }

    public Label update(Long id, Label label) {
        try (PreparedStatement preparedStatement = connection().prepareStatement(UPDATE_LABEL)) {
            preparedStatement.setString(1, label.getName());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return label;
    }

    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = connection().prepareStatement(DELETE_LABEL_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
