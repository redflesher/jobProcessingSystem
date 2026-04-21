package redflesher.job.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobStatusResponse {
    private Long id;
    private String status;
}
