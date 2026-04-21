package redflesher.job.worker.handler;

import redflesher.job.model.Job;
import redflesher.job.model.JobType;

public interface JobHandler {

    JobType getType();

    void validate(String payload);

    String handle(Job job) throws Exception;
}
