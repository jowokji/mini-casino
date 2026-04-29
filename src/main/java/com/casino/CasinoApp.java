package com.casino;

import java.util.Scanner;


public class CasinoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printBox(" MINI CASINO ");
        ConsoleFormatter.printLines(2);

        String playerName = "Player";

        ConsoleFormatter.printCentered("Enter your name: ");
        if (scanner.hasNextLine()) {
            playerName = scanner.nextLine();
            if (playerName.trim().isEmpty()) {
                playerName = "Player";
            }
        }

        double initialBalance = 1000.0;
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("You start with $" + initialBalance);

        Player player = new Player(playerName, initialBalance);
        Casino casino = new Casino(player, scanner);

        casino.start();

        scanner.close();
    }
}
