import fileio.SaveLoadManager;
import java.util.LinkedList;
import java.util.Queue;
import manager.ResourceManager;
import model.tasks.ColonyTask;
import model.tasks.EngineeringTask;
import model.tasks.LifeSupportTask;

public class TestSaveLoad {
    public static void main(String[] args) {
        ResourceManager rm = new ResourceManager();
        Queue<ColonyTask> queue = new LinkedList<>();

        queue.add(new LifeSupportTask());
        queue.add(new EngineeringTask());
        rm.addCredits(200);

        System.out.println("Before save:");
        System.out.println("Credits: " + rm.getCredits());
        System.out.println("Queue size: " + queue.size());

        SaveLoadManager.save(rm, queue);

        ResourceManager rm2 = new ResourceManager();
        Queue<ColonyTask> queue2 = new LinkedList<>();

        SaveLoadManager.load(rm2, queue2);

        System.out.println("\nAfter load:");
        System.out.println("Credits: " + rm2.getCredits());
        System.out.println("Queue size: " + queue2.size());
        for (ColonyTask task : queue2) {
            System.out.println(task);
        }
    }
}