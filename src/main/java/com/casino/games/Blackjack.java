package com.casino.games;

import com.casino.Game;
import com.casino.Player;
import com.casino.ConsoleFormatter;
import java.util.Scanner;


public class Blackjack implements Game {

    @Override
    public String getGameName() {
        return "Blackjack";
    }

    @Override
    public void play(Player player) {
        Scanner scanner = new Scanner(System.in);

        ConsoleFormatter.printLines(2);
        ConsoleFormatter.printCentered("BLACKJACK");
        ConsoleFormatter.printCentered("Get cards totaling 21 without going over!");
        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Balance: $" + String.format("%.2f", player.getBalance()));
        ConsoleFormatter.printLines(2);

        double bet = getBet(player, scanner);
        if (bet == 0) return;

        player.loseBet(bet);

        int playerSum = playRound(scanner);

        if (playerSum > 21) {
            ConsoleFormatter.printCentered("Bust! You went over 21. You lose!");
        } else {
            int dealerSum = getDealerCards();
            ConsoleFormatter.printCentered("Dealer has: " + dealerSum);

            if (dealerSum > 21) {
                ConsoleFormatter.printCentered("Dealer bust! You win!");
                player.addWinnings(bet * 2);
            } else if (playerSum > dealerSum) {
                ConsoleFormatter.printCentered("You win!");
                player.addWinnings(bet * 2);
            } else if (playerSum < dealerSum) {
                ConsoleFormatter.printCentered("Dealer wins!");
            } else {
                ConsoleFormatter.printCentered("Push! It's a tie.");
                player.addWinnings(bet);
            }
        }

        player.displayBalance();
        ConsoleFormatter.printLines(2);
        ConsoleFormatter.pause(scanner);
    }

    private int playRound(Scanner scanner) {
        int sum = 0;
        java.util.List<String> cards = new java.util.ArrayList<>();

        while (true) {
            int card = getRandomCard();
            sum += card;
            cards.add(String.valueOf(card));

            ConsoleFormatter.printLines(1);
            ConsoleFormatter.printCentered("Your cards:");
            ConsoleFormatter.displayCardsInRow(cards.toArray(new String[0]));
            ConsoleFormatter.printCentered("Total: " + sum);

            if (sum > 21) {
                break;
            }

            ConsoleFormatter.printCentered("Hit (h) or Stand (s)? ");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("s")) {
                break;
            }
        }

        return sum;
    }

    private int getDealerCards() {
        int sum = 0;
        java.util.List<String> cards = new java.util.ArrayList<>();
        while (sum < 17) {
            int card = getRandomCard();
            sum += card;
            cards.add(String.valueOf(card));
        }

        ConsoleFormatter.printLines(1);
        ConsoleFormatter.printCentered("Dealer's cards:");
        ConsoleFormatter.displayCardsInRow(cards.toArray(new String[0]));

        return sum;
    }

    private int getRandomCard() {
        return (int) (Math.random() * 10) + 2;
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
