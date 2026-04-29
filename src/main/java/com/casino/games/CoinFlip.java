package com.casino.games;

import com.casino.Game;
import com.casino.Player;
import com.casino.ConsoleFormatter;
import java.util.Scanner;

/**
 * Coin Flip game implementation
 */
public class CoinFlip implements Game {

    @Override
    public String getGameName() {
        return "Coin Flip";
    }

    @Override
    public void play(Player player) {
        Scanner scanner = new Scanner(System.in);

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("COIN FLIP");
        ConsoleFormatter.printCentered("Heads or Tails?");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);

        double bet = getBet(player, scanner);
        if (bet == 0) return;

        player.loseBet(bet);

        ConsoleFormatter.printCentered("Choose: Heads (h) or Tails (t)? ");
        String choice = scanner.nextLine().toLowerCase().trim();

        if (!choice.equals("h") && !choice.equals("t")) {
            ConsoleFormatter.printCentered("Invalid choice!");
            return;
        }

        boolean isHeads = Math.random() > 0.5;
        String result = isHeads ? "Heads" : "Tails";

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("The coin shows:");
        ConsoleFormatter.displayCoin(isHeads);

        boolean won = (choice.equals("h") && isHeads) || (choice.equals("t") && !isHeads);

        if (won) {
            ConsoleFormatter.printCentered("You win!");
            player.addWinnings(bet * 2);
        } else {
            ConsoleFormatter.printCentered("You lose!");
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
