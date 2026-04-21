package redflesher.job.worker;

import org.springframework.stereotype.Component;
import redflesher.job.model.Job;
import redflesher.job.queue.JobQueue;

@Component
public class JobWorker {

    private final JobQueue jobQueue;
    private final JobProcessor jobProcessor;

    public JobWorker(JobQueue jobQueue, JobProcessor jobProcessor) {
        this.jobQueue = jobQueue;
        this.jobProcessor = jobProcessor;
    }

    private void startWorker() {
        Thread workerThread = new Thread(() ->{
            while (true) {
                try {
                    Job job = jobQueue.take();
                    jobProcessor.process(job);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        workerThread.setDaemon(true);
        workerThread.start();
    }
}
