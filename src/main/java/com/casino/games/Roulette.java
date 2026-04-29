package com.casino.games;

import com.casino.Game;
import com.casino.Player;
import com.casino.ConsoleFormatter;
import java.util.Scanner;


public class Roulette implements Game {

    @Override
    public String getGameName() {
        return "Roulette";
    }

    @Override
    public void play(Player player) {
        Scanner scanner = new Scanner(System.in);

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("ROULETTE");
        ConsoleFormatter.printCentered("Bet on Red or Black (0-36)");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);

        double bet = getBet(player, scanner);
        if (bet == 0) return;

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Choose your bet:");
        ConsoleFormatter.printCentered("1. Red");
        ConsoleFormatter.printCentered("2. Black");
        ConsoleFormatter.printCentered("3. Odd");
        ConsoleFormatter.printCentered("4. Even");
        ConsoleFormatter.printCentered("Choose (1-4): ");

        String choice = scanner.nextLine();
        int spinResult = spin();

        player.loseBet(bet);

        String betType = null;
        boolean won = false;

        switch (choice) {
            case "1":
                betType = "Red";
                won = isRed(spinResult) && spinResult != 0;
                break;
            case "2":
                betType = "Black";
                won = isBlack(spinResult);
                break;
            case "3":
                betType = "Odd";
                won = spinResult % 2 == 1;
                break;
            case "4":
                betType = "Even";
                won = spinResult % 2 == 0 && spinResult != 0;
                break;
            default:
                ConsoleFormatter.printCentered("Invalid choice!");
                return;
        }

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("Spinning the wheel...");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.displayRouletteResult(spinResult);

        if (spinResult == 0) {
            ConsoleFormatter.printCentered("0! House wins!");
        } else if (won) {
            ConsoleFormatter.printCentered("You bet on " + betType + " and won!");
            player.addWinnings(bet * 2);
        } else {
            ConsoleFormatter.printCentered("You lost!");
        }

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.pause(scanner);
    }

    private int spin() {
        return (int) (Math.random() * 37);
    }

    private boolean isRed(int number) {
        return (number >= 1 && number <= 10) || (number >= 19 && number <= 28) ||
                (number >= 32 && number <= 36);
    }

    private boolean isBlack(int number) {
        return (number >= 11 && number <= 18) || (number >= 29 && number <= 31);
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
