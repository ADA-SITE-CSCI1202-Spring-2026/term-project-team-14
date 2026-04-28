package model.processors;

import model.tasks.ColonyTask;

public class HydroponicsModule implements IProcessor {

    @Override
    public boolean canProcess(ColonyTask task) {
        return task.getTaskType().equals("RESEARCH");
    }

    @Override
    public void processTask(ColonyTask task) {
        System.out.println("HydroponicsModule is processing: " + task.getTaskName());
    }
}