package fourman.project1.service.post;

import fourman.project1.domain.post.Post;
import fourman.project1.domain.post.PostRequestDto;
import fourman.project1.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * 전체 조회는 find + 테이블 이름 + s
 * 단 건 조회는  find + 테이블 이름 + byId
 * 생성은 create + 테이블 이름
 * 수정은 update + 테이블 이름
 * 삭제는 delete + 테이블 이름
 */

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findPosts() {
        return postRepository.findPosts();
    }

    public Post findPostById(Long id) {
        return postRepository.findPostById(id).orElse(null);
    }

    public Post createPost(Post post) {
//        Post post = new Post();
//        post.setTitle(postRequestDto.getTitle());
//        post.setBody(postRequestDto.getBody());
        return postRepository.createPost(post);
    }

    public Post updatePost(Post post) {
        Post findPost = postRepository.findPostById(post.getPostId()).orElse(null);

        if (findPost != null) {
            Optional.ofNullable(post.getTitle()).ifPresent(title -> findPost.setTitle(title));
            Optional.ofNullable(post.getBody()).ifPresent(body -> findPost.setBody(body));
        }

        return postRepository.updatePost(findPost);
    }

    public void deletePost(Long id) {
        postRepository.deletePost(id);
    }
}