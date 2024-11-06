package fourman.project1.domain.test;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper {

    Test testRequestDtoToTest(TestRequestDto testRequestDto);

    TestResponseDto testToTestResponseDto(Test test);
}
