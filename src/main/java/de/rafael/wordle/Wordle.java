package de.rafael.wordle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.math.BigInteger;
import java.util.ArrayList;

public class Wordle {


    public void createWindow(Stage stage) {
        final short WINDOW_WIDTH = 700;
        final short WINDOW_HEIGHT = 900;
        Pane root = new Pane();
        Scene mainWindow = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle("Wordle");
        stage.setScene(mainWindow);
        stage.setResizable(false);
        stage.show();
    }


}
