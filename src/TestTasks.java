import model.tasks.ColonyTask;
import model.tasks.EngineeringTask;
import model.tasks.LifeSupportTask;
import model.tasks.ResearchTask;

public class TestTasks {
    public static void main(String[] args) {
        ColonyTask task1 = new LifeSupportTask();
        ColonyTask task2 = new EngineeringTask();
        ColonyTask task3 = new ResearchTask();

        System.out.println(task1);
        System.out.println("Resources: " + task1.getRequiredResources());
        System.out.println("Reward: " + task1.getCreditReward());

        System.out.println(task2);
        System.out.println("Resources: " + task2.getRequiredResources());
        System.out.println("Reward: " + task2.getCreditReward());

        System.out.println(task3);
        System.out.println("Resources: " + task3.getRequiredResources());
        System.out.println("Reward: " + task3.getCreditReward());
    }
}