package fourman.project1.controller.post;

import fourman.project1.domain.post.Post;
import fourman.project1.domain.post.PostMapper;
import fourman.project1.domain.post.PostRequestDto;
import fourman.project1.domain.post.PostResponseDto;
import fourman.project1.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping
    public String findPosts(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "";
    }

    @GetMapping("/{postId}")
    public String findPostById(@PathVariable Long postId, Model model) {
        Post post = postService.findPostById(postId);
        model.addAttribute("post", post);
        return "";
    }

    @GetMapping("/create")
    public String createPost(Model model) {
        return "";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostRequestDto postRequestDto, Model model, RedirectAttributes redirectAttributes) {
        Post newPost = postService.createPost(postMapper.postRequestDtoToPost(postRequestDto));
        PostResponseDto postResponseDto = postMapper.postToPostResponseDto(newPost);
//        model.addAttribute("post", postResponseDto);
//        redirectAttributes.addAttribute("post", postResponseDto);
        return "redirect:/";
    }

    @PatchMapping("postId")
    public String updatePost(@PathVariable Long postId, @ModelAttribute PostRequestDto postRequestDto, Model model) {
        Post post = postMapper.postRequestDtoToPost(postRequestDto);
        post.setPostId(postId);

        Post updatePost = postService.updatePost(post);
        PostResponseDto postResponseDto = postMapper.postToPostResponseDto(updatePost);

        model.addAttribute("post", postResponseDto);
        return "";
    }

    @DeleteMapping("{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "";
    }
}
