package fourman.project1.domain.test;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Test {

    private Long testId;

    private String url;

    private Long vus;

    private Long duration;

    private Long rps;
}
