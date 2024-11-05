package fourman.project1.domain.user;

import fourman.project1.domain.post.Post;

import java.time.ZonedDateTime;
import java.util.List;


public class User {

    private Long userId;

    private String username;

    private String password;

    private List<Post> posts;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private ZonedDateTime deletedAt;
}
