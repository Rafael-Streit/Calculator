package de.rafael.guicalculator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class GUICalculator {
    private long buttonPosition = 0;
    private final Button addButton = new Button("+");
    private final Button subtractButton = new Button("-");
    private final Button divideButton = new Button("/");
    private final Button multiplyButton = new Button("x");
    private final Button sumButton = new Button("=");
    private final ArrayList<Button> buttonsNumber = new ArrayList<>(14);
    private final ArrayList<Button> buttonsOperator = new ArrayList<>(5);
    private String firstNumberAsString = "";
    private String secondNumberAsString = "";
    private BigInteger sum = new BigInteger("0");
    private boolean isOperatorPressed;
    private String operator = "";
    private Label output = new Label();
    private String textOutput = "";

    public Scene createWindow(Stage stage) {
        final short WINDOW_WIDTH = 700;
        final short WINDOW_HEIGHT = 900;
        Pane root = new Pane();
        Scene mainWindow = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setResizable(false);
        for (int i = 0; i <= 9; i++) {
            Button b = new Button(String.valueOf(i));
            b.setLayoutX(50 + (i % 3) * 100);
            b.setLayoutY(50 + (i / 3) * 50);
            b.setPrefSize(60, 50);
            buttonsNumber.add(b);
        }
        addButton(root, addButton);
        addButton(root, subtractButton);
        addButton(root, divideButton);
        addButton(root, multiplyButton);
        addButton(root, sumButton);
        root.getChildren().addAll(buttonsNumber);
        getCalculation();
        root.getChildren().add(this.output);
        return mainWindow;
    }

    private void addButton(Pane root, Button button) {
        button.setLayoutX(400);
        button.setLayoutY(50 + buttonPosition);
        button.setPrefSize(60, 50);
        root.getChildren().add(button);
        this.buttonsOperator.add(button);
        buttonPosition += 70;
    }

    private void getCalculation() {
        this.buttonsNumber.forEach(button -> button.setOnAction(e -> {
            if (!this.isOperatorPressed) {
                this.firstNumberAsString += button.getText();

                this.textOutput += this.firstNumberAsString + " ";
                this.output.setText(this.textOutput);
                System.out.println("First: " + this.firstNumberAsString);
            } else {
                if(this.firstNumberAsString.isEmpty()){
                    this.firstNumberAsString = "0";
                    this.textOutput += this.firstNumberAsString + " ";
                    this.output.setText(this.textOutput);
                }

                this.secondNumberAsString += button.getText();
                this.textOutput += this.secondNumberAsString + " ";
                this.output.setText(this.textOutput);
                System.out.println("Second: " + this.secondNumberAsString);
            }
        }));

        this.buttonsOperator.forEach(button -> button.setOnAction(e -> {
            String text = button.getText();
            if (text.equals("=")) {
                if (!firstNumberAsString.isEmpty() && !secondNumberAsString.isEmpty()) {
                    BigInteger firstNumber = new BigInteger(firstNumberAsString);
                    BigInteger secondNumber = new BigInteger(secondNumberAsString);
                    this.sum = switch (this.operator) {
                        case "+" -> firstNumber.add(secondNumber);
                        case "-" -> firstNumber.subtract(secondNumber);
                        case "x" -> firstNumber.multiply(secondNumber);
                        case "/" -> !secondNumber.equals(BigInteger.ZERO) ? firstNumber.divide(secondNumber) : BigInteger.ZERO;
                        default -> BigInteger.ZERO;
                    };
                    this.textOutput += "= " + this.sum;
                    this.output.setText(this.textOutput);
                    System.out.println("Result: " + sum);

                    this.firstNumberAsString = sum.toString();
                    this.secondNumberAsString = "";
                    this.isOperatorPressed = false;
                    this.firstNumberAsString = "";
                }
            } else {
                this.operator = text;
                this.textOutput += this.operator +" ";
                this.output.setText(this.textOutput);
                this.isOperatorPressed = true;
                System.out.println("Operator: " + operator);
            }

        }));
    }

    public void getDirectoryInformation() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "cmd", "/c",
                "echo Start && dir && echo Fertig"
        );
        Process process = processBuilder.start();
        StringBuilder filesInDirectory = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while((line = reader.readLine()) != null){
                filesInDirectory.append(line).append("\n");
            }
        }
        String contentOfDirectory = filesInDirectory.toString();
        System.out.println(contentOfDirectory);
    }
}