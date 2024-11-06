package fourman.project1.config.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("fourman.project1.repository.test") //
public class TestTrafficMyBatisConfig {
}
