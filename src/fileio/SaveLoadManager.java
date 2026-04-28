package fileio;

import manager.ResourceManager;
import model.Resource;
import model.tasks.ColonyTask;
import model.tasks.LifeSupportTask;
import model.tasks.EngineeringTask;
import model.tasks.ResearchTask;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class SaveLoadManager {

    private static final String SAVE_FILE = "save_data.txt";

    public static void save(ResourceManager rm, Queue<ColonyTask> queue) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE));

            writer.println("CREDITS:" + rm.getCredits());
            writer.println("OXYGEN:" + rm.getQuantity(Resource.OXYGEN));
            writer.println("SPARE_PARTS:" + rm.getQuantity(Resource.SPARE_PARTS));
            writer.println("RATIONS:" + rm.getQuantity(Resource.RATIONS));

            writer.println("QUEUE_START");
            for (ColonyTask task : queue) {
                writer.println(task.getTaskType());
            }
            writer.println("QUEUE_END");

            writer.close();
            System.out.println("Game saved!");
        } catch (IOException e) {
            System.out.println("ERROR: Could not save game.");
        }
    }

    public static void load(ResourceManager rm, Queue<ColonyTask> queue) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE));
            String line;
            boolean readingQueue = false;
            queue.clear();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("CREDITS:")) {
                    int credits = Integer.parseInt(line.split(":")[1]);
                    int diff = credits - rm.getCredits();
                    rm.addCredits(diff);
                } else if (line.startsWith("OXYGEN:")) {
                    int val = Integer.parseInt(line.split(":")[1]);
                    rm.restock(Resource.OXYGEN, val - rm.getQuantity(Resource.OXYGEN));
                } else if (line.startsWith("SPARE_PARTS:")) {
                    int val = Integer.parseInt(line.split(":")[1]);
                    rm.restock(Resource.SPARE_PARTS, val - rm.getQuantity(Resource.SPARE_PARTS));
                } else if (line.startsWith("RATIONS:")) {
                    int val = Integer.parseInt(line.split(":")[1]);
                    rm.restock(Resource.RATIONS, val - rm.getQuantity(Resource.RATIONS));
                } else if (line.equals("QUEUE_START")) {
                    readingQueue = true;
                } else if (line.equals("QUEUE_END")) {
                    readingQueue = false;
                } else if (readingQueue) {
                    if (line.equals("LIFE_SUPPORT")) queue.add(new LifeSupportTask());
                    else if (line.equals("ENGINEERING")) queue.add(new EngineeringTask());
                    else if (line.equals("RESEARCH")) queue.add(new ResearchTask());
                }
            }
            reader.close();
            System.out.println("Game loaded!");
        } catch (IOException e) {
            System.out.println("ERROR: Could not load game.");
        }
    }
}