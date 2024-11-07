package fourman.project1.repository.traffic;

import fourman.project1.domain.traffic.Traffic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrafficMyBatisMapper {

    void createTestTraffic(Traffic test);
}
