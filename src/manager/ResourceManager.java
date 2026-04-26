package manager;

import model.Resource;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    private HashMap<Resource, Integer> inventory;
    private int credits;

    public ResourceManager() {
        inventory = new HashMap<>();
        inventory.put(Resource.OXYGEN, 100);
        inventory.put(Resource.SPARE_PARTS, 20);
        inventory.put(Resource.RATIONS, 50);
        credits = 500;
    }

    public boolean hasEnough(Map<Resource, Integer> required) {
        for (Map.Entry<Resource, Integer> entry : required.entrySet()) {
            if (inventory.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public boolean consume(Map<Resource, Integer> required) {
        if (!hasEnough(required)) {
            System.out.println("ERROR: Not enough resources!");
            return false;
        }
        for (Map.Entry<Resource, Integer> entry : required.entrySet()) {
            int current = inventory.get(entry.getKey());
            inventory.put(entry.getKey(), current - entry.getValue());
        }
        return true;
    }

    public void restock(Resource r, int amount) {
        int current = inventory.getOrDefault(r, 0);
        inventory.put(r, current + amount);
    }

    public int getQuantity(Resource r) {
        return inventory.getOrDefault(r, 0);
    }

    public int getCredits() {
        return credits;
    }

    public void addCredits(int amount) {
        credits += amount;
    }

    public HashMap<Resource, Integer> getInventory() {
        return new HashMap<>(inventory);
    }

    public int getRestockCost(Resource r) {
        if (r == Resource.OXYGEN)
            return 50;
        if (r == Resource.SPARE_PARTS)
            return 30;
        if (r == Resource.RATIONS)
            return 20;
        return 0;
    }

    public boolean restockWithCost(Resource r, int amount) {
        int cost = getRestockCost(r) * amount;
        if (credits < cost) {
            System.out.println("ERROR: Not enough credits to restock!");
            return false;
        }
        credits -= cost;
        restock(r, amount);
        return true;
    }
}