package model.processors;

import model.tasks.ColonyTask;

public class EngineeringBay implements IProcessor {

    @Override
    public boolean canProcess(ColonyTask task) {
        String type = task.getTaskType();
        return type.equals("ENGINEERING") || type.equals("LIFE_SUPPORT");
    }

    @Override
    public void processTask(ColonyTask task) {
        System.out.println("EngineeringBay is processing: " + task.getTaskName());
    }
}