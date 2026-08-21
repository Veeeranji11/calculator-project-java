package com.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;   // <-- add this
import java.io.IOException; // <-- and this
import java.io.BufferedReader;
import java.io.FileReader;


public class Calculator {
    private double result = 0;
    private List<String> history = new ArrayList<>();
    // Add this inside Calculator class
    public void saveHistoryToFile() {
        try (FileWriter writer = new FileWriter("history.txt")) {
            for (String h : history) {
                writer.write(h + "\n");
            }
            System.out.println("History saved to history.txt");
        } catch (IOException e) {
            System.out.println("Error saving history: " + e.getMessage());
        }
    }
    public List<String> getHistory() {
        return history;
    }

    public void loadHistoryFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("history.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
            System.out.println("History loaded from history.txt");
        } catch (IOException e) {
            System.out.println("No previous history found.");
        }
    }


    public void add(double num) {
        result += num;
        history.add(result + " (added " + num + ")");
    }

    public void subtract(double num) {
        result -= num;
        history.add(result + " (subtracted " + num + ")");
    }

    public void multiply(double num) {
        result *= num;
        history.add(result + " (multiplied by " + num + ")");
    }

    public void divide(double num) {
        if (num != 0) {
            result /= num;
            history.add(result + " (divided by " + num + ")");
        } else {
            history.add("Error: Division by zero");
        }
    }

    public void showHistory() {
        System.out.println("Calculation History:");
        for (String h : history) {
            System.out.println(h);
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();
        calc.loadHistoryFromFile();  // Load old history at startup


        char choice;
        do {
            System.out.println("\nChoose operation: + - * / h(history) q(quit)");
            choice = scanner.next().charAt(0);

            switch (choice) {
                case '+':
                    System.out.print("Enter number: ");
                    calc.add(scanner.nextDouble());
                    break;
                case '-':
                    System.out.print("Enter number: ");
                    calc.subtract(scanner.nextDouble());
                    break;
                case '*':
                    System.out.print("Enter number: ");
                    calc.multiply(scanner.nextDouble());
                    break;
                case '/':
                    System.out.print("Enter number: ");
                    calc.divide(scanner.nextDouble());
                    break;
                case 'h':
                    calc.showHistory();
                    break;
                case 'q':
                    System.out.println("Exiting...");
                    break;
                case 's':
                    calc.saveHistoryToFile();
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 'q');

        scanner.close();
    }
}

