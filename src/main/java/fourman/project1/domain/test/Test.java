package fourman.project1.domain.test;

import fourman.project1.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Test {

    private Long testId;

    private String url;

    private String vus;

    private String duration;

    private Long rps;

    private Post post;
}
