package engine;

import manager.ResourceManager;
import model.processors.IProcessor;
import model.tasks.ColonyTask;
import java.util.List;

public class TaskExecutor {

    private ResourceManager resourceManager;
    private List<IProcessor> processors;

    public TaskExecutor(ResourceManager rm, List<IProcessor> processors) {
        this.resourceManager = rm;
        this.processors = processors;
    }

    public String executeTask(ColonyTask task) {
        if (task == null) {
            return "ERROR: No tasks in queue!";
        }

        if (!resourceManager.hasEnough(task.getRequiredResources())) {
            return "ERROR: Not enough resources for " + task.getTaskName();
        }

        for (IProcessor processor : processors) {
            if (processor.canProcess(task)) {
                resourceManager.consume(task.getRequiredResources());
                resourceManager.addCredits(task.getCreditReward());
                processor.processTask(task);
                return "SUCCESS: " + task.getTaskName() + " completed! +" + task.getCreditReward() + " credits";
            }
        }

        return "ERROR: No processor found for " + task.getTaskName();
    }
}
