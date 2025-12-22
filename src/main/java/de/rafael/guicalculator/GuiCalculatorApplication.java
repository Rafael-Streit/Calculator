package de.rafael.guicalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiCalculatorApplication extends Application {
    GUICalculator guiCalculator = new GUICalculator();
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Calculator");
        Scene mainScene = this.guiCalculator.createWindow(stage);
        stage.setScene(mainScene);
        stage.show();
    }
}
