package fourman.project1.domain.traffic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Traffic {

    private Long testId;

    private String url;

    private int vus;

    private String duration;

    private int rps;

    public static Traffic from(TrafficRequestDto testRequestDto) {
        Traffic test = new Traffic();
        test.setUrl(testRequestDto.getUrl());
        test.setVus(testRequestDto.getVus());
        test.setDuration(testRequestDto.getDuration());

        return test;
    }
}
