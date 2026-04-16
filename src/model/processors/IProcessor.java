package model.processors;

import model.tasks.ColonyTask;

public interface IProcessor {
    boolean canProcess(ColonyTask task);
    void processTask(ColonyTask task);
}