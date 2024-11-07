package fourman.project1.domain.traffic;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrafficMapper {

    Traffic testRequestDtoToTest(TrafficRequestDto testRequestDto);

    TrafficResponseDto testRequestDtoToTestResponseDto(TrafficRequestDto testRequestDto);
}
