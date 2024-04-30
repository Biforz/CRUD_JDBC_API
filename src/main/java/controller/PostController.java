package controller;

import model.Post;
import service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService;

    public PostController() {
        this.postService = new PostService();
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(Long id) {
        return postService.getPostById(id);
    }

    public void addPost(Post post) {
        postService.addPost(post);
    }

    public void updatePost(Long id, Post post) {
        postService.updatePost(id, post);
    }

    public void deletePost(Long id) {
        postService.deletePost(id);
    }
}
