package repository.jdbc;

import model.Label;
import model.Post;
import model.status.PostStatus;
import repository.PostRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static config.ConfigDataBase.connection;
import static util.DButil.showListLabelOnPost;

public class JdbcPostRepositoryImpl implements PostRepository {
    private static final String SHOW_ALL_POST_AND_LABEL =
            "SELECT p.id, p.content, p.created, p.updated, p.status, l.id, l.name " +
                    "FROM post p " +
                    "LEFT JOIN label_post lp ON p.id = lp.post_id " +
                    "LEFT JOIN label l ON lp.label_id = l.id";
    private static final String SHOW_POST_AND_LABEL_BY_ID =
            "SELECT p.id, p.content, p.created, p.updated, p.status, l.id, l.name " +
                    "FROM post p " +
                    "LEFT JOIN label_post lp ON p.id = lp.post_id " +
                    "LEFT JOIN label l ON lp.label_id = l.id WHERE p.id = ?";

    private static final String ADD_NEW_POST =
            "INSERT INTO post(content, created, updated, status) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_POST_BY_ID =
            "UPDATE post SET content = ?, updated = ?, status = ? WHERE id = ?";
    private static final String DELETE_POST_BY_ID = "UPDATE post SET status = ? WHERE id = ?";
    private static final String ADD_LABEL_IN_POST = "INSERT INTO label_post(post_id, label_id) VALUES (?, ?)";

    public List<Post> showAll() {
        List<Post> postList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection().prepareStatement(SHOW_ALL_POST_AND_LABEL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Post post;
            while (resultSet.next()) {
                post = new Post();
                post.setId(resultSet.getLong(1));
                post.setContent(resultSet.getString(2));
                post.setCreated(resultSet.getTimestamp(3).toLocalDateTime());
                post.setUpdated(resultSet.getTimestamp(4).toLocalDateTime());
                post.setPostStatus(PostStatus.valueOf(resultSet.getString(5)));
                post.setLabelList(showListLabelOnPost(post.getId()));
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    public Post showById(Long id) {
        Post post = new Post();
        try (PreparedStatement preparedStatement = connection().prepareStatement(SHOW_POST_AND_LABEL_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            post.setId(resultSet.getLong(1));
            post.setContent(resultSet.getString(2));
            post.setCreated(resultSet.getTimestamp(3).toLocalDateTime());
            post.setUpdated(resultSet.getTimestamp(4).toLocalDateTime());
            post.setPostStatus(PostStatus.valueOf(resultSet.getString(5)));
            post.setLabelList(showListLabelOnPost(post.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    public Post add(Post post) {
        try (PreparedStatement preparedStatement = connection().prepareStatement(ADD_NEW_POST)) {
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setTimestamp(2, new Timestamp(new Date().getTime()));
            preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
            preparedStatement.setString(4, post.getPostStatus().name());
            preparedStatement.executeUpdate();
            addIdLabelInPost(post.getId(), post.getLabelList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    public Post update(Long id, Post post) {
        try (PreparedStatement preparedStatement = connection().prepareStatement(UPDATE_POST_BY_ID)) {
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setTimestamp(2, new Timestamp(new Date().getTime()));
            preparedStatement.setString(3, post.getPostStatus().name());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    public void deleteById(Long id) {
        if (id == null) {
            return;
        }
        try (PreparedStatement preparedStatement = connection().prepareStatement(DELETE_POST_BY_ID)) {
            preparedStatement.setString(1, PostStatus.DELETED.name());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addIdLabelInPost(Long id, List<Label> labelList) {
        if (labelList == null) {
            return;
        }

        try (PreparedStatement preparedStatement = connection().prepareStatement(ADD_LABEL_IN_POST)) {
            for (Label label : labelList) {
                if (label.getId() != null) {
                    preparedStatement.setLong(1, id);
                    preparedStatement.setLong(2, label.getId());
                }
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
