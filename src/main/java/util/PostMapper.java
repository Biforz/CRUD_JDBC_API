package util;

import model.Post;
import model.status.PostStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapper {
    public static Post mappingPost(ResultSet resultSet) throws SQLException {
        return Post.builder()
                .id(resultSet.getLong(1))
                .content(resultSet.getString(2))
                .created(resultSet.getTimestamp(3).toLocalDateTime())
                .updated(resultSet.getTimestamp(4).toLocalDateTime())
                .postStatus(PostStatus.valueOf(resultSet.getString(5)))
                .build();
    }
}
