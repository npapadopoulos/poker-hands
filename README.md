# **Poker Hands**
## Problem
In the card game poker, a hand consists of five cards. Hands are ranked, from lowest to highest, in the following way:

- **High Card**: Highest value card.
- **One Pair**: Two cards of the same value.
- **Two Pairs**: Two different pairs.
- **Three of a Kind**: Three cards of the same value.
- **Straight**: All cards are consecutive values.
- **Flush**: All cards of the same suit.
- **Full House**: Three of a kind and a pair.
- **Four of a Kind**: Four cards of the same value.
- **Straight Flush**: All cards are consecutive values of same suit.
- **Royal Flush**: Ten, Jack, Queen, King, Ace, in same suit.

The cards are ranked, from lowest to highest, in the following order:
2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

Each card has one of the following suits:
Clubs (C), Diamonds (D), Hearts (H) or Spades (S).

If two or more players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights beats a pair of fives
(see example 1 below). But if two ranks tie, for example, both players have a pair of queens, then highest cards in each hand are compared (see
example 4 below); if the highest cards tie then the next highest cards are compared, and so on. If a tie cannot be broken, the pot is split equally
between all matching hands.

Consider the following five hands dealt to multiple players:

|  **Hand** | **Player 1**  | **Player 2**  | **Player 3**  | **Winner**  |
|---|---|---|---|---|
|  **1** | 1 5H 5C 6S 7S KD<br>Pair of Fives  |2C 3S 8S 8D TD<br>Pair of Eights   | 5D 8C 9S JS AC<br>Highest card Ace  |  Player 2 |
|  **2** | 5D 8C 9S JS AC<br>Highest card Ace  |  2C 5C 7D 8S QH<br>Highest card Queen | 2C 5C 7D 8S KH<br>Highest card King  |  Player 1 |
|  **3** | 2D 9C AS AH AC<br>Three Aces  | 3D 6D 7D TD QD<br>Flush with Diamonds  |  2C 5C 7D 8S QH<br>Highest card Queen | Player 2  |
|  **4** | 4D 6S 9H QH QC<br>Pair of Queens<br>Highest card Nine  | 3D 6D 7H QD QS<br>Pair of Queens<br>Highest card Seven  |  2C 5C 7D 8S QH<br>Highest card Queen | Player 1  |
|  **5** | 2H 2D 4C 4D 4S<br>Full House<br>With Three Fours  | 3C 3D 3S 9S 9D<br>Full House<br>with Three Threes  | 2C 5C 7D 8S QH<br>Highest card Queen  | Player 1 |

## Challenge
Write a Java application that evaluates poker hands of two or more players as described above. Think of your code as production-ready code. Your
application should be written in Java >=11 and built with Apache Maven. The application should not use any external libraries. You may use external
libraries for testing purposes.

Usage

```
$> java -jar poker-hands.jar "2D 9C AS AH AC" "3D 6D 7D TD QD" "2C 5C 7C 8S QH" ...
Ranking:
 1 Player 2 3D 6D 7D TD QD Flush, Diamonds
 2 Player 1 2D 9C AS AH AC Three Of a Kind, Ace
 3 Player 3 2C 5C 7C 8S QH High Card, Queen

Player 2 wins.
```
Each argument passed to the application can be considered as a valid string representing a players hand: each card is represented as **\<card\>\<suit\>**
with **\<card\> = { 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A }** and **\<suit\> = { C, D, H, S }**. Cards are separated with a space.
The application ranks each hand compared to the other hands and prints a list of hands sorted by rank in descending order. Additionally, a description
of each hand is printed. Finally, the application prints the winner.


