import java.util.ArrayList;
import java.util.List;
import model.processors.EngineeringBay;
import model.processors.IProcessor;
import model.processors.MedicalWard;
import model.tasks.ColonyTask;
import model.tasks.EngineeringTask;
import model.tasks.LifeSupportTask;
import model.tasks.ResearchTask;

public class TestProcessors {
    public static void main(String[] args) {
        List<IProcessor> processors = new ArrayList<>();
        processors.add(new EngineeringBay());
        processors.add(new MedicalWard());

        ColonyTask[] tasks = {
            new LifeSupportTask(),
            new EngineeringTask(),
            new ResearchTask()
        };

        for (ColonyTask task : tasks) {
            System.out.println("Task: " + task);
            boolean found = false;
            for (IProcessor processor : processors) {
                if (processor.canProcess(task)) {
                    processor.processTask(task);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("No processor found for: " + task.getTaskName());
            }
            System.out.println();
        }
    }
}