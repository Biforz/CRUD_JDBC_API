package repository.jdbc;

import model.Post;
import model.status.PostStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.PostRepository;
import service.PostService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class JdbcPostRepositoryImplTest {
    private final PostRepository postRepository = Mockito.mock(PostRepository.class);
    private final PostService postService = new PostService(postRepository);
    private Post post;

    @BeforeEach
    void setUp() {
        post = Post.builder()
                .id(1L)
                .content("Content")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .postStatus(PostStatus.ACTIVE)
                .build();
    }

    @Test
    void getAll() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);

        when(postService.getAllPosts()).thenReturn(posts);

        List<Post> postActualList = postService.getAllPosts();

        assertNotNull(postActualList);
        assertEquals(posts, postActualList);
    }

    @Test
    void getById() {
        when(postService.getPostById(1L)).thenReturn(post);

        Post postActual = postService.getPostById(1L);

        assertNotNull(postActual);
        assertEquals(post, postActual);
        assertEquals(postActual.getId(), post.getId());
        assertEquals(postActual.getContent(), post.getContent());
        assertEquals(postActual.getCreated(), post.getCreated());
        assertEquals(postActual.getUpdated(), post.getUpdated());
        assertEquals(postActual.getPostStatus(), post.getPostStatus());
    }

    @Test
    void create() {
        when(postService.addPost(post)).thenReturn(post);

        Post postActual = postService.addPost(post);

        assertNotNull(postActual);
        assertEquals(postActual.getId(), post.getId());
        assertEquals(postActual.getContent(), post.getContent());
        assertEquals(postActual.getCreated(), post.getCreated());
        assertEquals(postActual.getUpdated(), post.getUpdated());
        assertEquals(postActual.getPostStatus(), post.getPostStatus());
    }

    @Test
    void update() {
        when(postService.updatePost(1L, post)).thenReturn(post);

        Post postActual = postService.updatePost(1L, post);

        assertNotNull(postActual);
        assertEquals(postActual.getId(), post.getId());
        assertEquals(postActual.getContent(), post.getContent());
        assertEquals(postActual.getCreated(), post.getCreated());
        assertEquals(postActual.getUpdated(), post.getUpdated());
        assertEquals(postActual.getPostStatus(), post.getPostStatus());
    }

    @Test
    void deleteById() {
        postService.deletePost(1L);
        verify(postRepository, times(1)).deleteById(1L);
    }
}
