package fourman.project1.domain.post;

import fourman.project1.domain.user.User;
import fourman.project1.domain.board.Board;

import java.time.ZonedDateTime;

public class Post {

    private Long postId;

    private String title;

    private String body;

    private User user;

    private Board board;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private ZonedDateTime deletedAt;
}
