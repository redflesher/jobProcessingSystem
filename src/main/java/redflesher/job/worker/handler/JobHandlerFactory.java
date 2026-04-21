package redflesher.job.worker.handler;

import org.springframework.stereotype.Component;
import redflesher.job.model.JobType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JobHandlerFactory {

    private final Map<JobType, JobHandler> handlerMap;

    public JobHandlerFactory(List<JobHandler> handlers) {
        this.handlerMap = handlers.stream()
                .collect(Collectors.toMap(JobHandler::getType, h -> h));
    }

    public JobHandler getHandler(JobType type) {
        JobHandler handler = handlerMap.get(type);
        if (handler == null) {
            throw new IllegalArgumentException("No handler for type: " + type);
        }
        return handler;
    }
}
