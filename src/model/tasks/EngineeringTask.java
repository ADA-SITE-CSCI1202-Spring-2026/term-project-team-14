package model.tasks;

import model.Resource;
import java.util.HashMap;
import java.util.Map;

public class EngineeringTask extends ColonyTask {

    public EngineeringTask() {
        super("Solar Array Repair", "Solar panel is broken", createResources(), 100);
    }

    private static Map<Resource, Integer> createResources() {
        Map<Resource, Integer> resources = new HashMap<>();
        resources.put(Resource.SPARE_PARTS, 3);
        return resources;
    }

    @Override
    public String getTaskType() {
        return "ENGINEERING";
    }
}