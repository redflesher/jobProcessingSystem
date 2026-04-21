package redflesher.job.queue;

import org.springframework.stereotype.Component;
import redflesher.job.model.Job;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class JobQueue {

    private final BlockingQueue<Job> queue = new LinkedBlockingQueue<>();

    public void enqueue(Job job) {
        queue.offer(job);
    }

    public Job take() throws InterruptedException {
        return queue.take();
    }
}
