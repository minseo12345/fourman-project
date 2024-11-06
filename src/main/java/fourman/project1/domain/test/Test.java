package fourman.project1.domain.test;

import fourman.project1.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Test {

    private Long testId;

    private String url;

    private int vus;

    private String duration;

    private int rps;

    private Post post;

    public static Test of(String url, int vus, String duration) {
        Test test = new Test();
        test.setUrl(url);
        test.setVus(vus);
        test.setDuration(duration);

        return test;
    }
}
