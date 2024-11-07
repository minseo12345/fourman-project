package fourman.project1.service.traffic;

import fourman.project1.domain.traffic.Traffic;
import fourman.project1.exception.traffic.TrafficErrorException;
import fourman.project1.repository.traffic.TrafficMyBatisMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestTrafficServiceImpl implements TrafficService {

    private final TrafficMyBatisMapper testTrafficMyBatisMapper;

    @Override
    public void createTestTraffic(Traffic test) {

        String SCRIPT_PATH = "/Users/zun/Lecture/Elice/Cloud/project1/k6/script.js";

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
                throw new TrafficErrorException("k6 command failed with exit code: " + exitCode);
            }

            testTrafficMyBatisMapper.createTestTraffic(test);
        } catch (IOException | InterruptedException e) {
            log.error("Error executing k6 command", e);
            throw new TrafficErrorException("k6 command execution failed", e);
        }
    }
}
