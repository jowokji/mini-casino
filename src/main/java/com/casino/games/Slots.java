package com.casino.games;

import com.casino.Game;
import com.casino.Player;
import com.casino.ConsoleFormatter;
import java.util.Scanner;

/**
 * Slot Machine game implementation
 */
public class Slots implements Game {
    private static final String[] SYMBOLS = {"A", "B", "C", "D", "E", "F"};

    @Override
    public String getGameName() {
        return "Slot Machine";
    }

    @Override
    public void play(Player player) {
        Scanner scanner = new Scanner(System.in);

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("SLOT MACHINE");
        ConsoleFormatter.printCentered("Match 3 symbols to win!");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);

        double bet = getBet(player, scanner);
        if (bet == 0) return;

        player.loseBet(bet);

        String[] reels = spinReels();

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Spinning reels...");
        ConsoleFormatter.printLines(1);

        displayReelsAnimated(reels);

        ConsoleFormatter.printLines(1);
        if (checkWin(reels)) {
            double winnings = bet * getMultiplier(reels);
            ConsoleFormatter.printCentered("JACKPOT! You won!");
            player.addWinnings(winnings);
        } else {
            ConsoleFormatter.printCentered("No match. Better luck next time!");
        }

        player.displayBalance();
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.pause(scanner);
    }

    private String[] spinReels() {
        String[] result = new String[3];
        for (int i = 0; i < 3; i++) {
            result[i] = SYMBOLS[(int) (Math.random() * SYMBOLS.length)];
        }
        return result;
    }

    private void displayReels(String[] reels) {
        ConsoleFormatter.printCentered("+-----+-----+-----+");
        ConsoleFormatter.printCentered("|  " + reels[0] + "  |  " + reels[1] + "  |  " + reels[2] + "  |");
        ConsoleFormatter.printCentered("+-----+-----+-----+");
    }

    private void displayReelsAnimated(String[] reels) {
        // Show first reel
        ConsoleFormatter.printCentered("+-----+-----+-----+");
        ConsoleFormatter.printCentered("|  " + reels[0] + "  |     |     |");
        ConsoleFormatter.printCentered("+-----+-----+-----+");
        ConsoleFormatter.printLines(1);

        // Show second reel
        ConsoleFormatter.printCentered("+-----+-----+-----+");
        ConsoleFormatter.printCentered("|  " + reels[0] + "  |  " + reels[1] + "  |     |");
        ConsoleFormatter.printCentered("+-----+-----+-----+");
        ConsoleFormatter.printLines(1);

        // Show third reel
        ConsoleFormatter.printCentered("+-----+-----+-----+");
        ConsoleFormatter.printCentered("|  " + reels[0] + "  |  " + reels[1] + "  |  " + reels[2] + "  |");
        ConsoleFormatter.printCentered("+-----+-----+-----+");
    }

    private boolean checkWin(String[] reels) {
        return reels[0].equals(reels[1]) && reels[1].equals(reels[2]);
    }

    private int getMultiplier(String[] reels) {
        if (reels[0].equals("F")) {
            return 10;
        } else if (reels[0].equals("E")) {
            return 8;
        } else {
            return 3;
        }
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
