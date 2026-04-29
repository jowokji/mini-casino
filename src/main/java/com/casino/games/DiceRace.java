package com.casino.games;

import com.casino.Game;
import com.casino.Player;
import com.casino.ConsoleFormatter;
import java.util.Scanner;


public class DiceRace implements Game {

    @Override
    public String getGameName() {
        return "Dice Race";
    }

    @Override
    public void play(Player player) {
        Scanner scanner = new Scanner(System.in);

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("DICE RACE");
        ConsoleFormatter.printCentered("Roll higher than the dealer!");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);

        double bet = getBet(player, scanner);
        if (bet == 0) return;

        player.loseBet(bet);

        ConsoleFormatter.printCentered("Press Enter to roll your dice...");
        scanner.nextLine();

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printSideBySide("Your Roll:", "Dealer's Roll:");

        int[] playerDice = rollThreeDice();
        int[] dealerDice = rollThreeDice();

        String playerDiceStr = "[" + playerDice[0] + "] [" + playerDice[1] + "] [" + playerDice[2] + "]";
        String dealerDiceStr = "[" + dealerDice[0] + "] [" + dealerDice[1] + "] [" + dealerDice[2] + "]";
        ConsoleFormatter.printSideBySide(playerDiceStr, dealerDiceStr);

        int playerRoll = playerDice[0] + playerDice[1] + playerDice[2];
        int dealerRoll = dealerDice[0] + dealerDice[1] + dealerDice[2];

        ConsoleFormatter.printLines(1);
        String playerTotal = "Total: " + playerRoll;
        String dealerTotal = "Total: " + dealerRoll;
        ConsoleFormatter.printSideBySide(playerTotal, dealerTotal);

        ConsoleFormatter.printLines(1);
        if (playerRoll > dealerRoll) {
            ConsoleFormatter.printCentered("You win!");
            player.addWinnings(bet * 2);
        } else if (playerRoll < dealerRoll) {
            ConsoleFormatter.printCentered("Dealer wins!");
        } else {
            ConsoleFormatter.printCentered("It's a tie!");
            player.addWinnings(bet);
        }

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.pause(scanner);
    }

    private int rollDice() {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += (int) (Math.random() * 6) + 1;
        }
        return sum;
    }

    private int[] rollThreeDice() {
        int[] dice = new int[3];
        for (int i = 0; i < 3; i++) {
            dice[i] = (int) (Math.random() * 6) + 1;
        }
        return dice;
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
