# Mini Casino

A console-based casino game with 10 different games to play.

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## How to Run

```bash
mvn clean package
java -cp target/mini_casino-1.0-SNAPSHOT.jar com.casino.CasinoApp
```

## Game Overview

Start with $1000 and play your favorite casino games. The goal is to win as much money as possible before going broke.

### Available Games

1. **Blackjack** - Get 21 or beat the dealer without busting (Win: 2x bet)
2. **Slots** - Match 3 symbols to win (Multiplier varies: 3x to 10x)
3. **Roulette** - Bet on Red/Black/Odd/Even (0-36) (Win: 2x bet)
4. **Craps** - Roll 7 or 11 to win (Win: 2x bet)
5. **High/Low** - Guess if card is High (8+) or Low (2-7) (Win: 2x bet)
6. **Number Guess** - Guess the secret number 1-10 (Win: 10x bet)
7. **Coin Flip** - Choose Heads or Tails (Win: 2x bet)
8. **Dice Race** - Roll higher than the dealer with 3 dice (Win: 2x bet)
9. **Lucky Number** - Pick 1-6 and match up to 3 random draws (Win: 2x-50x bet)
10. **Card Battle** - Play a card higher than the dealer (Win: 2x bet)

## Gameplay

1. Enter your name when prompted
2. Select a game from the menu
3. Enter your bet amount
4. Play the game following the on-screen instructions
5. Win or lose money based on the outcome
6. Continue playing or return to the menu
7. The game ends when you run out of money

## Building

To build the project:

```bash
mvn clean package
```

This creates a JAR file at `target/mini_casino-1.0-SNAPSHOT.jar`
