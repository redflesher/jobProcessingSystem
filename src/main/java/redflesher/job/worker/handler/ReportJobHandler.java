package redflesher.job.worker.handler;

import org.springframework.stereotype.Component;
import redflesher.job.dto.ReportPayload;
import redflesher.job.model.Job;
import redflesher.job.model.JobType;
import tools.jackson.databind.ObjectMapper;

@Component
public class ReportJobHandler implements JobHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JobType getType() {
        return JobType.REPORT;
    }

    @Override
    public void validate(String payload) {
        try {
            ReportPayload reportPayload = objectMapper.readValue(payload, ReportPayload.class);

            if (reportPayload.getPayload() == null || reportPayload.getPayload().isBlank()) {
                throw new IllegalArgumentException("Payload cannot be empty");
            }
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid REPORT payload", exception);
        }

    }

    @Override
    public String handle(Job job) throws Exception {
        Thread.sleep(1000);
        return "Report generated for" + job.getPayload();
    }
}
