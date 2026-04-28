import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import engine.SimulationEngine;
import engine.TaskExecutor;
import fileio.SaveLoadManager;
import manager.ResourceManager;
import model.Resource;
import model.processors.EngineeringBay;
import model.processors.IProcessor;
import model.processors.MedicalWard;
import model.tasks.ColonyTask;
import java.util.ArrayList;
import java.util.List;

public class AresBaseApp extends Application {

    private SimulationEngine engine;
    private ResourceManager resourceManager;
    private TaskExecutor executor;
    private ListView<String> taskList;
    private TextArea logArea;
    private Label creditsLabel;
    private Label oxygenLabel;
    private Label partsLabel;
    private Label rationsLabel;

    @Override
    public void start(Stage stage) {

        resourceManager = new ResourceManager();
        engine = new SimulationEngine();

        List<IProcessor> processors = new ArrayList<>();
        processors.add(new EngineeringBay());
        processors.add(new MedicalWard());
        executor = new TaskExecutor(resourceManager, processors);

        VBox queuePanel = new VBox(10);
        queuePanel.setPadding(new Insets(10));
        queuePanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        Label queueTitle = new Label("Task Queue");
        taskList = new ListView<>();
        Button executeBtn = new Button("Execute Next Task");
        queuePanel.getChildren().addAll(queueTitle, taskList, executeBtn);

        VBox vitalsPanel = new VBox(10);
        vitalsPanel.setPadding(new Insets(10));
        vitalsPanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        Label vitalsTitle = new Label("Colony Vitals");
        creditsLabel = new Label("Credits: " + resourceManager.getCredits());
        oxygenLabel = new Label("Oxygen: " + resourceManager.getQuantity(Resource.OXYGEN));
        partsLabel = new Label("Spare Parts: " + resourceManager.getQuantity(Resource.SPARE_PARTS));
        rationsLabel = new Label("Rations: " + resourceManager.getQuantity(Resource.RATIONS));
        vitalsPanel.getChildren().addAll(vitalsTitle, creditsLabel, oxygenLabel, partsLabel, rationsLabel);

        VBox cargoPanel = new VBox(10);
        cargoPanel.setPadding(new Insets(10));
        cargoPanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        Label cargoTitle = new Label("Cargo Replicator");
        ComboBox<String> resourceDropdown = new ComboBox<>();
        resourceDropdown.getItems().addAll("OXYGEN", "SPARE_PARTS", "RATIONS");
        resourceDropdown.setValue("OXYGEN");
        Button synthesizeBtn = new Button("Synthesize");
        cargoPanel.getChildren().addAll(cargoTitle, resourceDropdown, synthesizeBtn);

        VBox logPanel = new VBox(10);
        logPanel.setPadding(new Insets(10));
        logPanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        Label logTitle = new Label("System Log");
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(150);
        logArea.appendText("System started...\n");
        logPanel.getChildren().addAll(logTitle, logArea);

        VBox saveLoadPanel = new VBox(10);
        saveLoadPanel.setPadding(new Insets(10));
        saveLoadPanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        Label saveLoadTitle = new Label("Save / Load");
        Button saveBtn = new Button("Save Game");
        Button loadBtn = new Button("Load Game");
        saveLoadPanel.getChildren().addAll(saveLoadTitle, saveBtn, loadBtn);

        executeBtn.setOnAction(e -> {
            ColonyTask task = engine.pollTask();
            String result = executor.executeTask(task);
            logArea.appendText(result + "\n");
            updateVitals();
            updateQueue();
        });

        synthesizeBtn.setOnAction(e -> {
            String selected = resourceDropdown.getValue();
            Resource r = Resource.valueOf(selected);
            boolean success = resourceManager.restockWithCost(r, 10);
            if (success) {
                logArea.appendText("Synthesized 10 " + selected + "\n");
            } else {
                logArea.appendText("ERROR: Not enough credits!\n");
            }
            updateVitals();
        });

        saveBtn.setOnAction(e -> {
            SaveLoadManager.save(resourceManager, engine.getTaskQueue());
            logArea.appendText("Game saved!\n");
        });

        loadBtn.setOnAction(e -> {
            SaveLoadManager.load(resourceManager, engine.getTaskQueue());
            logArea.appendText("Game loaded!\n");
            updateVitals();
            updateQueue();
        });

        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            ColonyTask newTask = engine.generateRandomTask();
            engine.addTask(newTask);
            logArea.appendText("WARNING: New Task - " + newTask.getTaskName() + "\n");
            updateQueue();
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(queuePanel, 0, 0);
        grid.add(vitalsPanel, 1, 0);
        grid.add(cargoPanel, 0, 1);
        grid.add(logPanel, 1, 1);
        grid.add(saveLoadPanel, 0, 2);

        Scene scene = new Scene(grid, 800, 600);
        stage.setTitle("Ares Base - Survival Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private void updateVitals() {
        creditsLabel.setText("Credits: " + resourceManager.getCredits());
        oxygenLabel.setText("Oxygen: " + resourceManager.getQuantity(Resource.OXYGEN));
        partsLabel.setText("Spare Parts: " + resourceManager.getQuantity(Resource.SPARE_PARTS));
        rationsLabel.setText("Rations: " + resourceManager.getQuantity(Resource.RATIONS));
    }

    private void updateQueue() {
        taskList.getItems().clear();
        for (ColonyTask task : engine.getTaskQueue()) {
            taskList.getItems().add(task.toString());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}