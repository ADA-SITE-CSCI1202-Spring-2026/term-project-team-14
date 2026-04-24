import engine.SimulationEngine;
import model.tasks.ColonyTask;

public class TestEngine {
    public static void main(String[] args) {
        SimulationEngine engine = new SimulationEngine();

        for (int i = 0; i < 5; i++) {
            ColonyTask task = engine.generateRandomTask();
            engine.addTask(task);
            System.out.println("Generated: " + task);
        }

        System.out.println("\nPolling tasks:");
        while (!engine.isEmpty()) {
            System.out.println("Polled: " + engine.pollTask());
        }
    }
}