package repository.jdbc;

import config.ConfigDataBase;
import model.Label;
import repository.LabelRepository;
import util.LabelMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcLabelRepositoryImpl implements LabelRepository {
    private static final String SHOW_ALL_LABELS = "SELECT * FROM label";
    private static final String SHOW_LABEL_BY_ID = "SELECT * FROM label WHERE id = ?";
    private static final String ADD_LABEL = "INSERT INTO label(name) VALUES (?)";
    private static final String UPDATE_LABEL = "UPDATE label SET name = ? WHERE id = ?";
    private static final String DELETE_LABEL_BY_ID = "DELETE FROM label WHERE id = ?";

    public List<Label> showAll() {
        List<Label> labelList = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConfigDataBase.preparedStatement(SHOW_ALL_LABELS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                labelList.add(LabelMapper.mappingLabel(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return labelList;
    }

    public Label showById(Long id) {
        Label label = null;
        try (PreparedStatement preparedStatement = ConfigDataBase.preparedStatement(SHOW_LABEL_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                label = LabelMapper.mappingLabel(resultSet);
            } else {
                throw new IllegalArgumentException("Такого id нет");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return label;
    }

    public Label add(Label label) {
        try (PreparedStatement preparedStatement = ConfigDataBase.preparedStatement(ADD_LABEL)) {
            preparedStatement.setString(1, label.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return label;
    }

    public Label update(Long id, Label label) {
        try (PreparedStatement preparedStatement = ConfigDataBase.preparedStatement(UPDATE_LABEL)) {
            preparedStatement.setString(1, label.getName());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return label;
    }

    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = ConfigDataBase.preparedStatement(DELETE_LABEL_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
