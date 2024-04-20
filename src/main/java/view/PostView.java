package view;

import controller.PostController;
import model.Post;
import model.status.PostStatus;

import java.util.Scanner;

public class PostView {
    private final Scanner scanner;
    private final PostController postController;

    public PostView() {
        this.scanner = new Scanner(System.in);
        this.postController = new PostController();
    }

    public void findAllPosts() {
        for (Post post : postController.getAllPosts()) {
            System.out.println(post);
        }
    }

    public void findPostById() {
        System.out.print("Введите id для поиска: ");
        long id = scanner.nextLong();
        System.out.println(postController.getPostById(id));
    }

    public void addNewPost() {
        Post post = new Post();
        System.out.print("Введите content: ");
        String content = scanner.nextLine();
        post.setContent(content);
        post.setPostStatus(PostStatus.ACTIVE);

        postController.addPost(post);
    }

    public void updatePostById() {
        Post post = new Post();
        System.out.print("Введите id для редактирования: ");
        long id = scanner.nextLong();

        scanner.nextLine();

        System.out.print("Введите context: ");
        String context = scanner.nextLine();

        post.setId(id);
        post.setContent(context);
        post.setCreated(post.getCreated());
        post.setPostStatus(PostStatus.UNDER_REVIEW);

        postController.updatePost(id, post);
    }

    public void deletePostById() {
        System.out.print("Введите id для удаления: ");
        long id = scanner.nextLong();
        postController.deletePost(id);
    }
}
