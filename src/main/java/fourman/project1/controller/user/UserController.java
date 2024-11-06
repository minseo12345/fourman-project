package fourman.project1.controller.user;
import org.springframework.ui.Model;

import fourman.project1.domain.user.User;
import fourman.project1.dto.user.CheckUsernameRequestDto;
import fourman.project1.dto.user.UserSignUpRequestDto;
import fourman.project1.mapper.mapstruct.SignUpMapper;
import fourman.project1.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SignUpMapper signUpMapper;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("userSignUpRequestDto", new UserSignUpRequestDto());
        return "join";
    }

    // 회원가입
    @PostMapping("/join")
    public String signUp(@ModelAttribute UserSignUpRequestDto userSignUpRequestDto) {
        User user = signUpMapper.toEntity(userSignUpRequestDto);
//        System.out.println(user.getUsername());
        userService.signUp(user);
        return "redirect:/login";
    }

    // 중복확인
    @PostMapping("/check-username")
    @ResponseBody
    public boolean checkUsername(@RequestBody CheckUsernameRequestDto checkUsernameRequestDto) {
        return userService.isUsernameAvailable(checkUsernameRequestDto.getUsername());
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userSignUpRequestDto", new UserSignUpRequestDto());
        return "login";
    }
}
