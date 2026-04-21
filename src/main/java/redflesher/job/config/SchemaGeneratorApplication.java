package redflesher.job.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "redflesher.job.model")
@EnableJpaRepositories(basePackages = "redflesher.job.repository")
public class SchemaGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchemaGeneratorApplication.class, args);
    }
}
