package fourman.project1.domain.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TestRequestDto {

    private String url;

    private int vus;

    private String duration;
}
