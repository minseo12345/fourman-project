package fourman.project1.controller.post;

import fourman.project1.domain.board.Board;
import fourman.project1.domain.post.Post;
import fourman.project1.domain.post.PostMapper;
import fourman.project1.domain.post.PostRequestDto;
import fourman.project1.domain.post.PostResponseDto;
import fourman.project1.domain.traffic.Traffic;
import fourman.project1.domain.traffic.TrafficMapper;
import fourman.project1.domain.traffic.TrafficRequestDto;
import fourman.project1.domain.user.User;
import fourman.project1.service.post.PostService;
import fourman.project1.service.traffic.TrafficService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/posts")
@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final TrafficService testTrafficService;
    private final TrafficMapper testMapper;

    @GetMapping
    public String findPosts(Model model) {
        // Posts 찾기
        List<Post> posts = postService.findPosts();

        log.info("find posts.size() ={}", posts.size());

        model.addAttribute("posts", posts);
        return "main-service";
    }

    @GetMapping("/{postId}")
    public String findPostById(@PathVariable Long postId, Model model) {
        // Post 찾기
        Post post = postService.findPostById(postId);

        log.info("findPostById() findPost={}, {}", post.getTitle(), post.getBody());

        model.addAttribute("post", post);
        return "detailed-service";
    }

    @GetMapping("/create")
    public String createPost(Model model) {
        return "create-service";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostRequestDto postRequestDto,
                             @ModelAttribute TrafficRequestDto testRequestDto,
                             Model model, RedirectAttributes redirectAttributes) {

        // User와 Board 찾기
        User findUser = new User(); // userService.findByUserId();
        Board findBoard = new Board(); // boardService.findByBoardId();

        // Post 생성 및 할당
        Post post = Post.from(postRequestDto);
        post.setUser(findUser);
        post.setBoard(findBoard);

        // Test 생성 및 할당
        Traffic test = Traffic.from(testRequestDto);
        testTrafficService.createTestTraffic(test);
        post.setTest(test);

        // post 저장 및 응답
        postService.createPost(post);
        PostResponseDto postResponseDto = postMapper.postToPostResponseDto(post);

        return "redirect:/posts";
    }

    @PatchMapping("/{postId}")
    public String updatePost(@PathVariable Long postId, @ModelAttribute PostRequestDto postRequestDto, Model model) {

        // User 찾기
        User findUser = new User();//        userService.findByUserId();
        // Board 찾기
        Board findBoard = new Board(); // boardService.findByBoardId();

        // Post로 매핑
        Post post = postMapper.postRequestDtoToPost(postRequestDto);
        post.setPostId(postId);
        post.setUser(findUser);
        post.setBoard(findBoard);

        // Post 업데이트
        postService.updatePost(post);
        log.info("updatePost() post={} , {}", post.getTitle(), post.getBody());

        // PostResponseDto 로 매핑
        PostResponseDto postResponseDto = postMapper.postToPostResponseDto(post);

        // model.addAttribute("post", postResponseDto);
        return "redirect:/";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId) {
        // Post 삭제
        postService.deletePost(postId);
        return "redirect:/";
    }
}
