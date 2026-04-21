package redflesher.job.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redflesher.job.dto.CreateJobRequest;
import redflesher.job.dto.JobResponse;
import redflesher.job.dto.JobResultResponse;
import redflesher.job.dto.JobStatusResponse;
import redflesher.job.model.Job;
import redflesher.job.service.JobService;

@RestController
@RequestMapping("api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Submit a job
    @PostMapping
    public ResponseEntity<JobResponse> submitJob(@Valid @RequestBody CreateJobRequest request) {

        Job job = jobService.createJob(
                request.getType(),
                request.getPayload()
        );

        return ResponseEntity.ok(
                JobResponse.builder()
                        .id(job.getId())
                        .status(job.getStatus().name())
                        .build()
        );
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<JobStatusResponse> getStatus(@PathVariable Long id) {

        Job job = jobService.getJobOrThrow(id);

        return ResponseEntity.ok(
                JobStatusResponse.builder()
                        .id(job.getId())
                        .status(job.getStatus().name())
                        .build()
        );
    }

    @GetMapping("/{id}/result")
    public ResponseEntity<JobResultResponse> getResult(@PathVariable Long id) {

        Job job = jobService.getJobOrThrow(id);

        return ResponseEntity.ok(
                JobResultResponse.builder()
                        .id(job.getId())
                        .status(job.getStatus().name())
                        .result(job.getResult())
                        .build()
        );
    }
}
