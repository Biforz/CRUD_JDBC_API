package service;

import lombok.RequiredArgsConstructor;
import model.Post;
import repository.PostRepository;
import repository.jdbc.JdbcPostRepositoryImpl;

import java.util.List;

@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostService() {
        this.postRepository = new JdbcPostRepositoryImpl();
    }

    public List<Post> getAllPosts() {
        return postRepository.showAll();
    }

    public Post getPostById(Long id) {
        return postRepository.showById(id);
    }

    public Post addPost(Post post) {
        return postRepository.add(post);
    }

    public Post updatePost(Long id, Post post) {
        return postRepository.update(id, post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
