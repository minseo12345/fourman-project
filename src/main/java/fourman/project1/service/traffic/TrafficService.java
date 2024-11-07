package fourman.project1.service.traffic;

import fourman.project1.domain.traffic.Traffic;

import java.util.List;

public interface TrafficService {

    List<Traffic> findTraffics();

    void createTraffic(Traffic test);
}
