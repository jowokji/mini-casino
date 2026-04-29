package com.casino.games;

import com.casino.Game;
import com.casino.Player;
import com.casino.ConsoleFormatter;
import java.util.Scanner;


public class NumberGuess implements Game {

    @Override
    public String getGameName() {
        return "Number Guess";
    }

    @Override
    public void play(Player player) {
        Scanner scanner = new Scanner(System.in);

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("NUMBER GUESS");
        ConsoleFormatter.printCentered("Guess a number between 1 and 10!");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);

        double bet = getBet(player, scanner);
        if (bet == 0) return;

        player.loseBet(bet);

        ConsoleFormatter.printCentered("Enter your guess (1-10): ");
        int guess = 0;
        try {
            guess = Integer.parseInt(scanner.nextLine().trim());
            if (guess < 1 || guess > 10) {
                ConsoleFormatter.printCentered("Invalid number! Must be between 1 and 10.");
                return;
            }
        } catch (NumberFormatException e) {
            ConsoleFormatter.printCentered("Invalid input!");
            return;
        }

        int secretNumber = (int) (Math.random() * 10) + 1;

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("The number was: " + secretNumber);
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.displayNumberLine(guess, secretNumber, 1, 10);

        if (guess == secretNumber) {
            ConsoleFormatter.printCentered("Correct! You won!");
            player.addWinnings(bet * 10);
        } else {
            ConsoleFormatter.printCentered("Wrong! Better luck next time.");
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
