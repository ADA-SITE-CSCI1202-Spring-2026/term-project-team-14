package model.tasks;

import model.Resource;
import java.util.Map;

public abstract class ColonyTask {

    protected String taskName;
    protected String description;
    protected Map<Resource, Integer> requiredResources;
    protected int creditReward;

    public ColonyTask(String taskName, String description, Map<Resource, Integer> requiredResources, int creditReward) {
        this.taskName = taskName;
        this.description = description;
        this.requiredResources = requiredResources;
        this.creditReward = creditReward;
    }

    public abstract String getTaskType();

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public Map<Resource, Integer> getRequiredResources() {
        return requiredResources;
    }

    public int getCreditReward() {
        return creditReward;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "] " + taskName;
    }
}