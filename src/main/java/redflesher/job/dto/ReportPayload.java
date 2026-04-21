package redflesher.job.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportPayload {
    private String payload;
}
