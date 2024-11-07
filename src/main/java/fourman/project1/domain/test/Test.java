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

    public static Test from(TestRequestDto testRequestDto) {
        Test test = new Test();
        test.setUrl(testRequestDto.getUrl());
        test.setVus(testRequestDto.getVus());
        test.setDuration(testRequestDto.getDuration());

        return test;
    }
}
