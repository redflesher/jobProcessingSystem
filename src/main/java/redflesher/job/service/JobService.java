package redflesher.job.service;

import org.springframework.stereotype.Service;
import redflesher.job.exception.JobNotFoundException;
import redflesher.job.model.Job;
import redflesher.job.model.JobStatus;
import redflesher.job.model.JobType;
import redflesher.job.queue.JobQueue;
import redflesher.job.repository.JobRepository;
import redflesher.job.worker.handler.JobHandler;
import redflesher.job.worker.handler.JobHandlerFactory;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobQueue jobQueue;
    private final JobHandlerFactory handlerFactory;

    public JobService(JobRepository jobRepository, JobQueue jobQueue, JobHandlerFactory handlerFactory) {
        this.jobRepository = jobRepository;
        this.jobQueue = jobQueue;
        this.handlerFactory = handlerFactory;
    }

    public Job createJob(JobType type, String payload) {

        JobHandler handler = handlerFactory.getHandler(type);

        handler.validate(payload);

        Job job = Job.builder()
                .type(type)
                .payload(payload)
                .status(JobStatus.PENDING)
                .build();

        Job saved = jobRepository.save(job);
        jobQueue.enqueue(saved);

        return saved;
    }

    public Job getJobOrThrow(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
    }

}
