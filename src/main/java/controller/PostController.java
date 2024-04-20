package controller;

import model.Post;
import repository.PostRepository;
import repository.jdbc.JdbcPostRepositoryImpl;

import java.util.List;

public class PostController {
    private final PostRepository postRepository;

    public PostController() {
        this.postRepository = new JdbcPostRepositoryImpl();
    }

    public List<Post> getAllPosts() {
        return postRepository.showAll();
    }

    public Post getPostById(Long id) {
        return postRepository.showById(id);
    }

    public void addPost(Post post) {
        postRepository.add(post);
    }

    public void updatePost(Long id, Post post) {
        postRepository.update(id, post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
