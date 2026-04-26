import manager.ResourceManager;
import model.Resource;

public class TestRestock {
    public static void main(String[] args) {
        ResourceManager rm = new ResourceManager();

        System.out.println("Credits: " + rm.getCredits());
        System.out.println("Oxygen: " + rm.getQuantity(Resource.OXYGEN));

        boolean result = rm.restockWithCost(Resource.OXYGEN, 10);
        System.out.println("Restock success: " + result);
        System.out.println("Credits after: " + rm.getCredits());
        System.out.println("Oxygen after: " + rm.getQuantity(Resource.OXYGEN));

        ResourceManager rm2 = new ResourceManager();
        rm2.restockWithCost(Resource.OXYGEN, 100);
    }
}