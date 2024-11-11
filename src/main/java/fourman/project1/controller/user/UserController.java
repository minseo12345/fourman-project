package fourman.project1.controller.user;
import fourman.project1.domain.user.UserMapper;

import org.springframework.ui.Model;

import fourman.project1.domain.user.User;
import fourman.project1.domain.user.CheckUsernameRequestDto;
import fourman.project1.domain.user.UserSignUpRequestDto;
import fourman.project1.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("userSignUpRequestDto", new UserSignUpRequestDto());

        return "/user/join";
    }

    @PostMapping("/join")
    public String signUp(@ModelAttribute UserSignUpRequestDto userSignUpRequestDto) {
        User user = User.builder()
                .username(userSignUpRequestDto.getUsername())
                .password(userSignUpRequestDto.getPassword())
                .build();

        userService.signUp(user);
        return "redirect:/login";
    }

    @PostMapping("/check-username")
    @ResponseBody
    public boolean checkUsername(@RequestBody CheckUsernameRequestDto checkUsernameRequestDto) {
        return userService.isUsernameAvailable(checkUsernameRequestDto.getUsername());
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userSignUpRequestDto", new UserSignUpRequestDto());
        return "/user/login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }


}
