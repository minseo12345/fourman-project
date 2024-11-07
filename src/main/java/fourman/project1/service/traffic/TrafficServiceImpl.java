package fourman.project1.service.traffic;

import fourman.project1.domain.traffic.Traffic;
import fourman.project1.exception.traffic.TrafficK6CmdErrorException;
import fourman.project1.exception.traffic.TrafficNotFoundException;
import fourman.project1.repository.traffic.TrafficMyBatisMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrafficServiceImpl implements TrafficService {

    private final TrafficMyBatisMapper trafficMyBatisMapper;

    @Override
    public List<Traffic> findTraffics() {
        return trafficMyBatisMapper.findTraffics();
    }

    @Override
    public Traffic findTrafficById(Long trafficId) {
        return trafficMyBatisMapper.findTrafficById(trafficId)
                    .orElseThrow(TrafficNotFoundException::new);
    }

    @Override
    public void createTraffic(Traffic traffic) {
        trafficMyBatisMapper.createTraffic(traffic);

        String LOCAL_HOST_URL = "http://localhost:8080";
        traffic.setUrl(LOCAL_HOST_URL + "/traffics/" + traffic.getTrafficId());

        String SCRIPT_PATH = "/Users/zun/Lecture/Elice/Cloud/project1/k6/script.js";

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "/opt/homebrew/bin/k6", "run",
                    "--vus", String.valueOf(traffic.getVus()),
                    "--duration", traffic.getDuration(),
                    "--env", "TARGET_URL=" + traffic.getUrl(),
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
                throw new TrafficK6CmdErrorException("execution failed with exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            trafficMyBatisMapper.forceDeleteTrafficDueToError(traffic.getTrafficId());
            throw new TrafficK6CmdErrorException(e);
        }
    }
}
