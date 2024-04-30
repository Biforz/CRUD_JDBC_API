package repository.jdbc;

import model.Post;
import model.Writer;
import repository.WriterRepository;
import util.PostMapper;
import util.WriterMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.ConfigDataBase.connection;

public class JdbcWriterRepositoryImpl implements WriterRepository {
    private static final String FIND_POST_ON_WRITER =
            "SELECT * FROM post p " +
                    "LEFT JOIN writer_post wp ON p.id = wp.posts_id " +
                    "LEFT JOIN label_post lp ON p.id = lp.post_id " +
                    "LEFT JOIN label l ON l.id = lp.label_id " +
                    "WHERE wp.writer_id = ?";
    private static final String SHOW_ALL_WRITER_ON_POST_ON_LABEL =
            "SELECT w.id, w.firstName, w.lastNAme, " +
                    "p.id, p.content, p.created, p.updated, p.status, l.id, l.name " +
                    "FROM writer w " +
                    "LEFT JOIN writer_post wp on w.id = wp.writer_id " +
                    "LEFT JOIN post p on wp.posts_id = p.id " +
                    "LEFT JOIN label_post lp on p.id = lp.post_id " +
                    "LEFT JOIN label l on lp.label_id = l.id";
    private static final String FIND_WRITER_BY_ID =
            "SELECT w.id, w.firstName, w.lastNAme, " +
                    "p.id, p.content, p.created, p.updated, p.status, l.id, l.name " +
                    "FROM writer w " +
                    "LEFT JOIN writer_post wp on w.id = wp.writer_id " +
                    "LEFT JOIN post p on wp.posts_id = p.id " +
                    "LEFT JOIN label_post lp on p.id = lp.post_id " +
                    "LEFT JOIN label l on lp.label_id = l.id " +
                    "WHERE w.id = ?";
    private static final String ADD_NEW_WRITER = "INSERT INTO writer(firstname, lastname) VALUES (?, ?)";
    private static final String UPDATE_WRITER_BY_ID = "UPDATE writer SET firstName = ?, lastName = ? WHERE id = ?";
    private static final String DELETE_WRITER_BY_ID = "DELETE FROM writer WHERE id = ?";

    public List<Writer> showAll() {
        List<Writer> writerList = new ArrayList<>();
        Writer writer;
        try (PreparedStatement preparedStatement = connection().prepareStatement(SHOW_ALL_WRITER_ON_POST_ON_LABEL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                writer = WriterMapper.mappingWriter(resultSet);
                writer.setPosts(showListPostAndLabelInWriter(writer.getId()));
                writerList.add(writer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writerList;
    }

    public Writer showById(Long id) {
        Writer writer = new Writer();
        try (PreparedStatement preparedStatement = connection().prepareStatement(FIND_WRITER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            writer = WriterMapper.mappingWriter(resultSet);
            writer.setPosts(showListPostAndLabelInWriter(writer.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    public Writer add(Writer writer) {
        try (PreparedStatement preparedStatement = connection().prepareStatement(ADD_NEW_WRITER)) {
            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    public Writer update(Long id, Writer writer) {
        try (PreparedStatement preparedStatement = connection().prepareStatement(UPDATE_WRITER_BY_ID)) {
            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = connection().prepareStatement(DELETE_WRITER_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Post> showListPostAndLabelInWriter(Long id) {
        List<Post> postList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection().prepareStatement(FIND_POST_ON_WRITER)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                postList.add(PostMapper.mappingPost(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }
}
