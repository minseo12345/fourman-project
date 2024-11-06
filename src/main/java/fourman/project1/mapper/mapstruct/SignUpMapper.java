package fourman.project1.mapper.mapstruct;

import fourman.project1.domain.user.User;
import fourman.project1.dto.user.UserSignUpRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SignUpMapper {
    SignUpMapper INSTANCE = Mappers.getMapper(SignUpMapper.class);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    User toEntity(UserSignUpRequestDto userSignUpRequestDto);

    UserSignUpRequestDto toDto(User user);


}
