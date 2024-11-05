package fourman.project1.repository.post;

import fourman.project1.domain.post.Post;
import fourman.project1.domain.post.PostRequestDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    public List<Post> findPosts() {
        return null;
    }


    public Optional<Post> findPostById(Long id) {
        return null;
    }

    public Post createPost(PostRequestDto postRequestDto) {
        return null;
    }

    public void deletePost(Long id) {
    }

    public Post updatePost(Post findPost) {
        return null;
    }
}
