package com.casino;

import com.casino.games.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Casino {
    private Player player;
    private List<Game> games;
    private Scanner scanner;

    public Casino(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
        this.games = new ArrayList<>();
        initializeGames();
    }

    private void initializeGames() {
        games.add(new Blackjack());
        games.add(new Slots());
        games.add(new Roulette());
        games.add(new Craps());
        games.add(new HighLow());
        games.add(new NumberGuess());
        games.add(new CoinFlip());
        games.add(new DiceRace());
        games.add(new LuckyNumber());
        games.add(new CardBattle());
    }

    public void start() {
        displayWelcome();

        while (!player.isBroke()) {
            displayMenu();
            int choice = getMenuChoice();

            if (choice == 0) {
                displayGoodbye();
                break;
            } else if (choice >= 1 && choice <= games.size()) {
                playGame(games.get(choice - 1));
            } else {
                ConsoleFormatter.printCentered("Invalid choice!");
            }
        }

        if (player.isBroke()) {
            displayGameOver();
        }
    }

    private void displayWelcome() {
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printBox(" WELCOME TO MINI CASINO ");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Welcome, " + player.getName() + "!");
        ConsoleFormatter.printCentered("Starting Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(3);
    }

    private void displayMenu() {
        ConsoleFormatter.printLines(3);
        ConsoleFormatter.printSeparator();
        ConsoleFormatter.printCentered("GAME MENU");
        ConsoleFormatter.printSeparator();
        ConsoleFormatter.printLines(2);

        for (int i = 0; i < games.size(); i++) {
            ConsoleFormatter.printCentered((i + 1) + ". " + games.get(i).getGameName());
        }

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("0. Exit Casino");
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);
    }

    private void playGame(Game game) {
        if (player.isBroke()) {
            displayGameOver();
            return;
        }
        game.play(player);
    }

    private int getMenuChoice() {
        while (true) {
            ConsoleFormatter.printCentered("Enter your choice: ");
            try {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    return Integer.parseInt(line);
                }
            } catch (NumberFormatException e) {
                ConsoleFormatter.printCentered("Please enter a valid number!");
            } catch (Exception e) {
                return 0;
            }
        }
    }

    private void displayGoodbye() {
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printBox(" Thanks for playing! Goodbye! ");
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("Final Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(3);
    }

    private void displayGameOver() {
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printBox(" GAME OVER - YOU'RE BROKE! ");
        ConsoleFormatter.printLines(3);
    }
}
