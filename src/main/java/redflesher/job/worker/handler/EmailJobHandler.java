package redflesher.job.worker.handler;

import org.springframework.stereotype.Component;
import redflesher.job.dto.EmailPayload;
import redflesher.job.model.Job;
import redflesher.job.model.JobType;
import tools.jackson.databind.ObjectMapper;

@Component
public class EmailJobHandler implements JobHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JobType getType() {
        return JobType.EMAIL;
    }

    @Override
    public void validate(String payload) {
        try {
            EmailPayload emailPayload = objectMapper.readValue(payload, EmailPayload.class);

            if (emailPayload.getTo() == null || emailPayload.getTo().isBlank()) {
                throw new IllegalArgumentException("Missing 'to' field");
            }
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid EMAIL payload", exception);
        }

    }

    @Override
    public String handle(Job job) throws Exception {
        Thread.sleep(1000); // simulate
        return "Email sent with payload: " + job.getPayload();
    }
}
