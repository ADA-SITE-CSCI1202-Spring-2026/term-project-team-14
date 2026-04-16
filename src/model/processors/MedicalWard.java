package model.processors;

import model.tasks.ColonyTask;

public class MedicalWard implements IProcessor {

    @Override
    public boolean canProcess(ColonyTask task) {
        return task.getTaskType().equals("LIFE_SUPPORT");
    }

    @Override
    public void processTask(ColonyTask task) {
        System.out.println("MedicalWard is processing: " + task.getTaskName());
    }
}