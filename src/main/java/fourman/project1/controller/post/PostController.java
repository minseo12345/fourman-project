package fourman.project1.controller.post;

import fourman.project1.domain.post.Post;
import fourman.project1.domain.post.PostRequestDto;
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

    @GetMapping
    public String createPost(Model model) {
        return "";
    }

    @PostMapping
    public String createPost(@ModelAttribute PostRequestDto postRequestDto, Model model, RedirectAttributes redirectAttributes) {
        Post newPost = postService.createPost(postRequestDto);
//        model.addAttribute("post", newPost);
//        redirectAttributes.addAttribute("post", newPost);
        return "redirect:/";
    }

    @PatchMapping
    public String updatePost(@ModelAttribute Post post, Model model) {
        Post updatePost = postService.updatePost(post);
        return "";
    }

    @DeleteMapping("{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "";
    }
}
