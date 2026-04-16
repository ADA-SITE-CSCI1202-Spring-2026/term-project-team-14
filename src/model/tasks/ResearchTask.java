package model.tasks;

import model.Resource;
import java.util.HashMap;
import java.util.Map;

public class ResearchTask extends ColonyTask {

    public ResearchTask() {
        super("Bio Sample Analysis", "Analyze soil samples from outside", createResources(), 60);
    }

    private static Map<Resource, Integer> createResources() {
        Map<Resource, Integer> resources = new HashMap<>();
        resources.put(Resource.RATIONS, 5);
        return resources;
    }

    @Override
    public String getTaskType() {
        return "RESEARCH";
    }
}