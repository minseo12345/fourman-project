package fourman.project1.domain.test;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TestRequestDto {

    private String url;

    private Long trafficRequest;

    private Long trafficDuration;
}
