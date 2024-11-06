package fourman.project1.repository.test;

import fourman.project1.domain.test.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestTrafficMyBatisMapper {

    void createTestTraffic(Test test);
}
