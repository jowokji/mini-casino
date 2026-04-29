package com.casino.games;

import com.casino.Game;
import com.casino.Player;
import com.casino.ConsoleFormatter;
import java.util.Scanner;


public class Craps implements Game {

    @Override
    public String getGameName() {
        return "Craps";
    }

    @Override
    public void play(Player player) {
        Scanner scanner = new Scanner(System.in);

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("CRAPS");
        ConsoleFormatter.printCentered("Roll dice and try to get lucky!");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);

        double bet = getBet(player, scanner);
        if (bet == 0) return;

        player.loseBet(bet);

        ConsoleFormatter.printCentered("Press Enter to roll the dice...");
        scanner.nextLine();

        int die1 = rollDie();
        int die2 = rollDie();
        int total = die1 + die2;

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Your roll:");
        ConsoleFormatter.displayDice(die1, die2);
        ConsoleFormatter.printCentered("Total: " + total);

        boolean won = evaluateRoll(total);

        ConsoleFormatter.printLines(1);
        if (won) {
            ConsoleFormatter.printCentered("You won!");
            player.addWinnings(bet * 2);
        } else {
            ConsoleFormatter.printCentered("You lost!");
        }

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.pause(scanner);
    }

    private int rollDie() {
        return (int) (Math.random() * 6) + 1;
    }

    private boolean evaluateRoll(int total) {
        // Win on 7 or 11, lose on 2, 3, or 12
        return total == 7 || total == 11;
    }

    private double getBet(Player player, Scanner scanner) {
        while (true) {
            ConsoleFormatter.printCentered("Enter your bet: $");
            try {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    double bet = Double.parseDouble(line);
                    if (player.canBet(bet) && bet > 0) {
                        return bet;
                    } else {
                        ConsoleFormatter.printCentered("Invalid bet amount!");
                    }
                }
            } catch (NumberFormatException e) {
                ConsoleFormatter.printCentered("Please enter a valid number!");
            } catch (Exception e) {
                return 0;
            }
        }
    }
}
