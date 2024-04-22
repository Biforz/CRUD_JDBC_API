package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.status.PostStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;
    private PostStatus postStatus;
    private List<Label> labels;
}
