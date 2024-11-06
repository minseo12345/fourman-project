package fourman.project1.service.test;

import fourman.project1.domain.test.Test;
import fourman.project1.exception.test.TestTrafficErrorException;
import fourman.project1.repository.test.TestTrafficMyBatisMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestTrafficServiceImpl implements TestTrafficService{

    private final TestTrafficMyBatisMapper testTrafficMyBatisMapper;

    @Override
    public void createTestTraffic(Test test) {

        String SCRIPT_PATH = "/Users/zun/Lecture/Elice/Cloud/project1/k6/script.js";
//        log.info("test vus {}", test.getVus());
//        log.info("test duration {}", test.getDuration());
//        log.info("test url {}", test.getUrl());

//        try {
//            ProcessBuilder pb = new ProcessBuilder(
//                    "/opt/homebrew/bin/k6", "run",
//                    "--vus", String.valueOf(test.getVus()),
//                    "--duration", test.getDuration(),
//                    "--env", "TARGET_URL=" + test.getUrl(),
//                    SCRIPT_PATH
//            );
//            log.info("pb {}", pb.command());
//            pb.environment().put("PATH", System.getenv("PATH"));
//
//            testTrafficMyBatisMapper.createTestTraffic(test);
//        } catch(RuntimeException e) {
//            log.error("createTestTraffic", e);
//            throw new TestTrafficErrorException();
//        }

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "/opt/homebrew/bin/k6", "run",
                    "--vus", String.valueOf(test.getVus()),
                    "--duration", test.getDuration(),
                    "--env", "TARGET_URL=" + test.getUrl(),
                    SCRIPT_PATH
            );
            pb.redirectErrorStream(true);
            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    log.info(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new TestTrafficErrorException("k6 command failed with exit code: " + exitCode);
            }

            testTrafficMyBatisMapper.createTestTraffic(test);
        } catch (IOException | InterruptedException e) {
            log.error("Error executing k6 command", e);
            throw new TestTrafficErrorException("k6 command execution failed", e);
        }
    }
}
