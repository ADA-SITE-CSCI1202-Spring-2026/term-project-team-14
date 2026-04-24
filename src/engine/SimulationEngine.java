package engine;

import model.tasks.ColonyTask;
import model.tasks.LifeSupportTask;
import model.tasks.EngineeringTask;
import model.tasks.ResearchTask;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class SimulationEngine {

    private Queue<ColonyTask> taskQueue;
    private Random random;

    public SimulationEngine() {
        taskQueue = new LinkedList<>();
        random = new Random();
    }

    public ColonyTask generateRandomTask() {
        int type = random.nextInt(3);
        if (type == 0) return new LifeSupportTask();
        if (type == 1) return new EngineeringTask();
        return new ResearchTask();
    }

    public void addTask(ColonyTask task) {
        taskQueue.add(task);
    }

    public ColonyTask pollTask() {
        return taskQueue.poll();
    }

    public Queue<ColonyTask> getTaskQueue() {
        return taskQueue;
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }
}