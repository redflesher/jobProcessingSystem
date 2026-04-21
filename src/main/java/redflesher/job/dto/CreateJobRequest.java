package redflesher.job.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import redflesher.job.model.JobType;

@Data
public class CreateJobRequest {

    @NotNull(message = "Job type is required")
    private JobType type;

    @NotNull(message = "Payload cannot be empty")
    private String payload;
}
