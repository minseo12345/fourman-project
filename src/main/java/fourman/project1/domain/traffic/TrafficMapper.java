package fourman.project1.domain.traffic;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrafficMapper {

    Traffic trafficRequestDtoToTraffic(TrafficRequestDto trafficRequestDto);

    TrafficResponseDto trafficRequestDtoToTrafficResponseDto(TrafficRequestDto trafficRequestDto);
}
