package fourman.project1.domain.post;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post postRequestDtoToPost(PostRequestDto postRequestDto);

    PostResponseDto postToPostResponseDto(Post post);
}
