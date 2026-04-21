package redflesher.job.worker;

import org.springframework.stereotype.Service;
import redflesher.job.model.Job;
import redflesher.job.model.JobStatus;
import redflesher.job.repository.JobRepository;
import redflesher.job.worker.handler.JobHandler;
import redflesher.job.worker.handler.JobHandlerFactory;

@Service
public class JobProcessor {

    private final JobRepository jobRepository;
    private final JobHandlerFactory jobHandlerFactory;

    public JobProcessor(JobRepository jobRepository, JobHandlerFactory jobHandlerFactory) {
        this.jobRepository = jobRepository;
        this.jobHandlerFactory = jobHandlerFactory;
    }

    public void process(Job job) {
        try {
            updateStatus(job, JobStatus.RUNNING);

            JobHandler handler = jobHandlerFactory.getHandler(job.getType());

            String result = handler.handle(job);

            job.setResult(result);
            updateStatus(job, JobStatus.SUCCESS);

        } catch (Exception exception) {
            job.setResult(exception.getMessage());
            updateStatus(job, JobStatus.FAILED);
        }
    }

    private void updateStatus(Job job, JobStatus status) {
        job.setStatus(status);
        jobRepository.save(job);
    }

}
