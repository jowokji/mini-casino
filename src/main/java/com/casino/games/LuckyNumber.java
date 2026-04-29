package com.casino.games;

import com.casino.Game;
import com.casino.Player;
import com.casino.ConsoleFormatter;
import java.util.Scanner;

/**
 * Lucky Number game implementation
 */
public class LuckyNumber implements Game {

    @Override
    public String getGameName() {
        return "Lucky Number";
    }

    @Override
    public void play(Player player) {
        Scanner scanner = new Scanner(System.in);

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("LUCKY NUMBER");
        ConsoleFormatter.printCentered("Pick a lucky number (1-6)!");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);

        double bet = getBet(player, scanner);
        if (bet == 0) return;

        player.loseBet(bet);

        ConsoleFormatter.printCentered("Enter your lucky number (1-6): ");
        int luckyNumber = 0;
        try {
            luckyNumber = Integer.parseInt(scanner.nextLine().trim());
            if (luckyNumber < 1 || luckyNumber > 6) {
                ConsoleFormatter.printCentered("Invalid number! Must be between 1 and 6.");
                return;
            }
        } catch (NumberFormatException e) {
            ConsoleFormatter.printCentered("Invalid input!");
            return;
        }

        int draw1 = (int) (Math.random() * 6) + 1;
        int draw2 = (int) (Math.random() * 6) + 1;
        int draw3 = (int) (Math.random() * 6) + 1;

        int matches = 0;
        if (draw1 == luckyNumber) matches++;
        if (draw2 == luckyNumber) matches++;
        if (draw3 == luckyNumber) matches++;

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Your lucky number: " + luckyNumber);
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Numbers drawn:");
        ConsoleFormatter.printCentered("+-----+ +-----+ +-----+");
        ConsoleFormatter.printCentered("| " + String.format("%2d", draw1) + "  | | " + String.format("%2d", draw2) + "  | | " + String.format("%2d", draw3) + "  |");
        ConsoleFormatter.printCentered("+-----+ +-----+ +-----+");

        ConsoleFormatter.printCentered((draw1 == luckyNumber ? "[HIT] " : "[X]   ") +
                (draw2 == luckyNumber ? "[HIT] " : "[X]   ") +
                (draw3 == luckyNumber ? "[HIT]" : "[X]  "));

        ConsoleFormatter.printLines(1);
        if (matches == 3) {
            ConsoleFormatter.printCentered("All three match! JACKPOT!");
            player.addWinnings(bet * 50);
        } else if (matches == 2) {
            ConsoleFormatter.printCentered("Two matches! You win big!");
            player.addWinnings(bet * 10);
        } else if (matches == 1) {
            ConsoleFormatter.printCentered("One match! You win something!");
            player.addWinnings(bet * 2);
        } else {
            ConsoleFormatter.printCentered("No matches. Try again!");
        }

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.pause(scanner);
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
