package fourman.project1.repository.traffic;

import fourman.project1.domain.traffic.Traffic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrafficMyBatisMapper {

    List<Traffic> findTraffics();

    void createTraffic(Traffic test);
}
