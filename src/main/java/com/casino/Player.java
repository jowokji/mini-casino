package com.casino;

/**
 * Represents a casino player
 */
public class Player {
    private String name;
    private double balance;

    public Player(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void addWinnings(double amount) {
        this.balance += amount;
    }

    public void loseBet(double amount) {
        this.balance -= amount;
    }

    public boolean canBet(double amount) {
        return balance >= amount;
    }

    public void displayBalance() {
        ConsoleFormatter.printCentered(String.format("Balance: $%.2f%n", balance));
    }

    public boolean isBroke() {
        return balance <= 0;
    }
}
