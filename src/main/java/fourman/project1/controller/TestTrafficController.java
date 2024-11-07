package fourman.project1.controller;

import fourman.project1.domain.test.Test;
import fourman.project1.domain.test.TestMapper;
import fourman.project1.domain.test.TestRequestDto;
import fourman.project1.service.test.TestTrafficService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
            @ModelAttribute TestRequestDto testRequestDto,
            Model model) {

//        log.info("testRequestDto url:{}", testRequestDto.getUrl());
//        log.info("testRequestDto vus: {}", testRequestDto.getVus());
//        log.info("testRequestDto duration: {}", testRequestDto.getDuration());

        // testTrafficService.createTestTraffic(testMapper.testRequestDtoToTest(testRequestDto));

        testTrafficService.createTestTraffic(Test.from(testRequestDto));
        model.addAttribute(
                "test", testMapper.testRequestDtoToTestResponseDto(testRequestDto)
        );

        return "redirect:/test";
    }
}
