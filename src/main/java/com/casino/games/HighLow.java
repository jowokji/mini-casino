package com.casino.games;

import com.casino.Game;
import com.casino.Player;
import com.casino.ConsoleFormatter;
import java.util.Scanner;


public class HighLow implements Game {

    @Override
    public String getGameName() {
        return "High/Low";
    }

    @Override
    public void play(Player player) {
        Scanner scanner = new Scanner(System.in);

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("HIGH/LOW");
        ConsoleFormatter.printCentered("Guess if the next card will be High (8+) or Low (2-7)!");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);

        double bet = getBet(player, scanner);
        if (bet == 0) return;

        player.loseBet(bet);

        int card = drawCard();
        String cardName = getCardName(card);

        ConsoleFormatter.printCentered("Card drawn:");
        ConsoleFormatter.displayCard(cardName);

        ConsoleFormatter.printCentered("Guess High (h) or Low (l)? ");
        String guess = scanner.nextLine().toLowerCase();

        boolean isHigh = card >= 8;
        boolean guessedHigh = guess.equals("h");

        ConsoleFormatter.printLines(1);
        if (guessedHigh == isHigh) {
            ConsoleFormatter.printCentered("Correct! You won!");
            player.addWinnings(bet * 2);
        } else {
            ConsoleFormatter.printCentered("Wrong! Better luck next time!");
        }

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.pause(scanner);
    }

    private int drawCard() {
        return (int) (Math.random() * 13) + 2;
    }

    private String getCardName(int value) {
        if (value == 11) return "J";
        if (value == 12) return "Q";
        if (value == 13) return "K";
        if (value == 14) return "A";
        return String.valueOf(value);
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
