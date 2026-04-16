package model.tasks;

import model.Resource;
import java.util.HashMap;
import java.util.Map;

public class LifeSupportTask extends ColonyTask {

    public LifeSupportTask() {
        super("Oxygen Leak", "There is an oxygen leak in module 3", createResources(), 150);
    }

    private static Map<Resource, Integer> createResources() {
        Map<Resource, Integer> resources = new HashMap<>();
        resources.put(Resource.OXYGEN, 20);
        resources.put(Resource.SPARE_PARTS, 2);
        return resources;
    }

    @Override
    public String getTaskType() {
        return "LIFE_SUPPORT";
    }
}