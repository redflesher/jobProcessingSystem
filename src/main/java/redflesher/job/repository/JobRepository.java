package redflesher.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import redflesher.job.model.Job;
import redflesher.job.model.JobStatus;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatus(JobStatus status);
}
