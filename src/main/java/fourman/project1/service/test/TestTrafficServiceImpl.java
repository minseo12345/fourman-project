package fourman.project1.service.test;

import fourman.project1.domain.test.Test;
import fourman.project1.exception.test.TestTrafficErrorException;
import fourman.project1.repository.test.TestTrafficMyBatisMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestTrafficServiceImpl implements TestTrafficService{

    private final TestTrafficMyBatisMapper testTrafficMyBatisMapper;

    @Override
    public Test createTestTraffic(Test test) {

        String SCRIPT_URL = "/Users/zun/Lecture/Elice/Cloud/project1/k6/script.js";

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "k6", "run", "--vus", String.valueOf(test.getVus()),
                    "duration", String.valueOf(test.getDuration()),
                    "--env", "TARGET_URL=" + test.getUrl(),
                    SCRIPT_URL
            );
            return testTrafficMyBatisMapper.createTestTraffic(test);
        } catch(RuntimeException e) {
            log.error("createTestTraffic", e);
            throw new TestTrafficErrorException();
        }
    }
}
