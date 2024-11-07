package fourman.project1.controller.traffic;

import fourman.project1.domain.traffic.Traffic;
import fourman.project1.domain.traffic.TrafficMapper;
import fourman.project1.domain.traffic.TrafficRequestDto;
import fourman.project1.service.traffic.TrafficService;
import jakarta.annotation.Priority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/traffics")
@RequiredArgsConstructor
public class TrafficController {

    private final TrafficMapper trafficMapper;
    private final TrafficService testTrafficService;

    @GetMapping
    public String findTraffics(Model model) {
        model.addAttribute("traffics", testTrafficService.findTraffics());
        return "main-traffic";
    }

    @PostMapping
    public String createTestTraffic(
            @ModelAttribute TrafficRequestDto trafficRequestDto,
            Model model) {

        testTrafficService.createTraffic(Traffic.from(trafficRequestDto));
        model.addAttribute(
                "traffic",
                trafficMapper.trafficRequestDtoToTrafficResponseDto(trafficRequestDto)
        );

        return "redirect:/traffics";
    }
}
