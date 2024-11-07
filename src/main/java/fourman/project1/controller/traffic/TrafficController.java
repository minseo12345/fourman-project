package fourman.project1.controller.traffic;

import fourman.project1.domain.traffic.Traffic;
import fourman.project1.domain.traffic.TrafficMapper;
import fourman.project1.domain.traffic.TrafficRequestDto;
import fourman.project1.service.traffic.TrafficService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TrafficController {

    private final TrafficMapper testMapper;
    private final TrafficService testTrafficService;

    @GetMapping
    public String refreshTestTraffic() {
        return "test";
    }

    @PostMapping
    public String createTestTraffic(
            @ModelAttribute TrafficRequestDto testRequestDto,
            Model model) {

        testTrafficService.createTestTraffic(Traffic.from(testRequestDto));
        model.addAttribute(
                "test", testMapper.testRequestDtoToTestResponseDto(testRequestDto)
        );

        return "redirect:/test";
    }
}
