package util;

import model.Post;
import model.status.PostStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper {
    public static Post mappingPost(ResultSet resultSet) throws SQLException {
        Post post = new Post();
        post.setId(resultSet.getLong(1));
        post.setContent(resultSet.getString(2));
        post.setCreated(resultSet.getTimestamp(3).toLocalDateTime());
        post.setUpdated(resultSet.getTimestamp(4).toLocalDateTime());
        post.setPostStatus(PostStatus.valueOf(resultSet.getString(5)));
        return post;
    }
}
