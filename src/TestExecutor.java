import engine.SimulationEngine;
import engine.TaskExecutor;
import manager.ResourceManager;
import model.processors.EngineeringBay;
import model.processors.IProcessor;
import model.processors.MedicalWard;
import model.tasks.ColonyTask;
import java.util.ArrayList;
import java.util.List;

public class TestExecutor {
    public static void main(String[] args) {
        ResourceManager rm = new ResourceManager();
        SimulationEngine engine = new SimulationEngine();

        List<IProcessor> processors = new ArrayList<>();
        processors.add(new EngineeringBay());
        processors.add(new MedicalWard());

        TaskExecutor executor = new TaskExecutor(rm, processors);

        engine.addTask(engine.generateRandomTask());
        engine.addTask(engine.generateRandomTask());
        engine.addTask(engine.generateRandomTask());

        System.out.println("Credits before: " + rm.getCredits());

        while (!engine.isEmpty()) {
            ColonyTask task = engine.pollTask();
            System.out.println(executor.executeTask(task));
        }

        System.out.println("Credits after: " + rm.getCredits());
    }
}
