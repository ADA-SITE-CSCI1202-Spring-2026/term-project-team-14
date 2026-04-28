import java.util.HashMap;
import java.util.Map;
import manager.ResourceManager;
import model.Resource;

public class TestResourceManager {
    public static void main(String[] args) {
        ResourceManager rm = new ResourceManager();

        System.out.println("Credits: " + rm.getCredits());
        System.out.println("Oxygen: " + rm.getQuantity(Resource.OXYGEN));
        System.out.println("Spare Parts: " + rm.getQuantity(Resource.SPARE_PARTS));
        System.out.println("Rations: " + rm.getQuantity(Resource.RATIONS));

        Map<Resource, Integer> needed = new HashMap<>();
        needed.put(Resource.OXYGEN, 20);
        needed.put(Resource.SPARE_PARTS, 2);

        boolean result = rm.consume(needed);
        System.out.println("Consumed: " + result);
        System.out.println("Oxygen after: " + rm.getQuantity(Resource.OXYGEN));

        Map<Resource, Integer> tooMuch = new HashMap<>();
        tooMuch.put(Resource.OXYGEN, 999);
        rm.consume(tooMuch);

        rm.restock(Resource.SPARE_PARTS, 10);
        System.out.println("Spare Parts after restock: " + rm.getQuantity(Resource.SPARE_PARTS));
    }
}