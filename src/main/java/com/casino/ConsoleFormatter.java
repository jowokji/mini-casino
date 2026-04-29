package com.casino;

import java.util.Scanner;


public class ConsoleFormatter {
    private static final int CONSOLE_WIDTH = 240;


    public static void printCentered(String text) {
        int padding = Math.max(0, (CONSOLE_WIDTH - text.length()) / 2);
        System.out.println(" ".repeat(padding) + text);
    }


    public static void println() {
        System.out.println();
    }


    public static void printLines(int count) {
        for (int i = 0; i < count; i++) {
            System.out.println();
        }
    }


    public static void printSeparator() {
        String line = "=".repeat(40);
        printCentered(line);
    }


    public static void printBox(String text) {
        String box = "╔" + "═".repeat(42) + "╗";
        int padding = Math.max(0, (CONSOLE_WIDTH - box.length()) /2);
        String content = "║" + centerString(text, 42) + "║";
        String bottom = "╚" + "═".repeat(42) + "╝";

        System.out.println(" ".repeat(padding) + box);
        System.out.println(" ".repeat(padding) + content);
        System.out.println(" ".repeat(padding) + bottom);
    }


    private static String centerString(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = (width - text.length()) / 2;
        int rightPadding = width - text.length() - padding;
        return " ".repeat(padding) + text + " ".repeat(rightPadding);
    }

    public static void pause(Scanner scanner) {
        printCentered("Press Enter to continue...");
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }


    public static void displayCard(String rank) {
        printCentered("+-----+");
        printCentered("|  " + padEnd(rank, 3) + "|");
        printCentered("+-----+");
    }


    public static void displayCardsInRow(String[] cards) {
        StringBuilder top = new StringBuilder();
        StringBuilder middle = new StringBuilder();
        StringBuilder bottom = new StringBuilder();

        for (String card : cards) {
            top.append("+-----+ ");
            middle.append("|  ").append(padEnd(card, 3)).append("| ");
            bottom.append("+-----+ ");
        }

        printCentered(top.toString());
        printCentered(middle.toString());
        printCentered(bottom.toString());
    }


    public static void displayCardsComparison(String card1, String card2) {
        printCentered("Your Card:           Dealer's Card:");
        printCentered("+-----+               +-----+");
        printCentered("|  " + padEnd(card1, 3) + "|               |  " + padEnd(card2, 3) + "|");
        printCentered("+-----+               +-----+");
    }


    public static void displayDice(int die1, int die2) {
        printCentered("[" + die1 + "]  [" + die2 + "]");
    }


    public static void displayThreeDice(int die1, int die2, int die3) {
        printCentered("[" + die1 + "]  [" + die2 + "]  [" + die3 + "]");
    }



    public static void displayNumberLine(int guess, int answer, int minNum, int maxNum) {
        StringBuilder line = new StringBuilder("[");
        for (int i = minNum; i <= maxNum; i++) {
            if (i == guess) {
                line.append("*").append(i).append("*");
            } else if (i == answer) {
                line.append("(").append(i).append(")");
            } else {
                line.append(i);
            }
            if (i < maxNum) line.append("-");
        }
        line.append("]");
        printCentered(line.toString());
    }


    public static void displaySlots(String reel1, String reel2, String reel3) {
        printCentered("+-----+-----+-----+");
        printCentered("|  " + reel1 + "  |  " + reel2 + "  |  " + reel3 + "  |");
        printCentered("+-----+-----+-----+");
    }


    public static void displayCoin(boolean isHeads) {
        printCentered("  +-------+");
        printCentered(" |   " + (isHeads ? "H" : "T") + "   |");
        printCentered("  +-------+");
    }


    public static void printSideBySide(String left, String right) {
        int colWidth = 35;
        String leftPadded = padEnd(left, colWidth);
        String combined = leftPadded + right;
        printCentered(combined);
    }


    public static void displayRouletteResult(int number) {
        printCentered("+-------+");
        printCentered("|  " + String.format("%2d", number) + "   |");
        printCentered("+-------+");
    }


    private static String padEnd(String str, int length) {
        if (str.length() >= length) return str;
        return str + " ".repeat(length - str.length());
    }
}
