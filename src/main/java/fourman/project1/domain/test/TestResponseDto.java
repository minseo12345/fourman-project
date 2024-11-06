package fourman.project1.domain.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TestResponseDto {

    private int vus;

    private String duration;

    private int rps;
}
