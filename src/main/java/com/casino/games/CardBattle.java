package com.casino.games;

import com.casino.Game;
import com.casino.Player;
import com.casino.ConsoleFormatter;
import java.util.Scanner;


public class CardBattle implements Game {

    @Override
    public String getGameName() {
        return "Card Battle";
    }

    @Override
    public void play(Player player) {
        Scanner scanner = new Scanner(System.in);

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("CARD BATTLE");
        ConsoleFormatter.printCentered("Play a card higher than the dealer!");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);

        double bet = getBet(player, scanner);
        if (bet == 0) return;

        player.loseBet(bet);

        int playerCard = drawCard();
        int dealerCard = drawCard();

        String playerCardName = getCardName(playerCard);
        String dealerCardName = getCardName(dealerCard);

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.displayCardsComparison(playerCardName, dealerCardName);

        ConsoleFormatter.printLines(1);
        if (playerCard > dealerCard) {
            ConsoleFormatter.printCentered("Your card is higher! You win!");
            player.addWinnings(bet * 2);
        } else if (playerCard < dealerCard) {
            ConsoleFormatter.printCentered("Dealer's card is higher. You lose.");
        } else {
            ConsoleFormatter.printCentered("Same card! It's a tie.");
            player.addWinnings(bet);
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
