package de.rafael.launcher;

import de.rafael.guicalculator.GUICalculator;
import de.rafael.wordle.Wordle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartingMenu extends Application {

    private int buttonPosition = 0;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene startWindow = new Scene(root, 600, 700);
        Button startCalculatorButton = new Button("Calculator");
        addButton(root, startCalculatorButton);
        Button startWordleButton = new Button("Wordle");
        addButton(root, startWordleButton);
        startCalculatorButton.setOnAction(e -> {
            new GUICalculator().createWindow(new Stage());
        });

        startWordleButton.setOnAction(e -> {
            new Wordle().createWindow(new Stage());
        });

        stage.setTitle("Rafael Launcher");
        stage.setResizable(false);
        stage.setScene(startWindow);
        stage.show();
    }

    private void addButton(Pane root, Button button) {
        button.setLayoutX(150);
        button.setLayoutY(50 + buttonPosition);
        button.setPrefSize(100, 50);
        root.getChildren().add(button);
        buttonPosition += 70;
    }
}


