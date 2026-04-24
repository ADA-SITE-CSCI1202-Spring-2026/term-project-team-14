import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AresBaseApp extends Application {

    @Override
    public void start(Stage stage) {

        VBox queuePanel = new VBox(10);
        queuePanel.setPadding(new Insets(10));
        queuePanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        Label queueTitle = new Label("Task Queue");
        ListView<String> taskList = new ListView<>();
        taskList.getItems().add("Waiting for tasks...");
        Button executeBtn = new Button("Execute Next Task");
        queuePanel.getChildren().addAll(queueTitle, taskList, executeBtn);

        VBox vitalsPanel = new VBox(10);
        vitalsPanel.setPadding(new Insets(10));
        vitalsPanel.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        Label vitalsTitle = new Label("Colony Vitals");
        Label creditsLabel = new Label("Credits: 500");
        Label oxygenLabel = new Label("Oxygen: 100");
        Label partsLabel = new Label("Spare Parts: 20");
        Label rationsLabel = new Label("Rations: 50");
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
        TextArea logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(150);
        logArea.appendText("System started...\n");
        logPanel.getChildren().addAll(logTitle, logArea);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(queuePanel, 0, 0);
        grid.add(vitalsPanel, 1, 0);
        grid.add(cargoPanel, 0, 1);
        grid.add(logPanel, 1, 1);

        Scene scene = new Scene(grid, 800, 600);
        stage.setTitle("Ares Base - Survival Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}