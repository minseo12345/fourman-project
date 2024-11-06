package fourman.project1.controller;

import fourman.project1.domain.test.TestMapper;
import fourman.project1.domain.test.TestRequestDto;
import fourman.project1.service.test.TestTrafficService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestTrafficController {

    private final TestMapper testMapper;
    private final TestTrafficService testTrafficService;

    @GetMapping
    public String refreshTestTraffic() {
        return "test";
    }

    @PostMapping
    public String createTestTraffic(
            TestRequestDto testRequestDto,
            Model model) {

        model.addAttribute(
                "test",
                testTrafficService.createTestTraffic(testMapper.testRequestDtoToTest(testRequestDto))
        );

        return "redirect:/test";
    }
}
