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

    // check if we have enough resources
    public boolean hasEnough(Map<Resource, Integer> required) {
        for (Map.Entry<Resource, Integer> entry : required.entrySet()) {
            if (inventory.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    // consume resources, returns false if not enough
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
}