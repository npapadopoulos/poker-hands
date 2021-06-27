package com.exasol.challenge.poker;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Map;
import java.util.stream.Stream;

public class Result {
    private static final Map<String, String> game = Map.of(
            "5H 5C 6S 7S KD,2C 3S 8S 8D TD,5D 8C 9S JS AC", "" +
                    "\nRanking:\n" +
                    "\t1\tPlayer 2\t2C 3S 8S 8D TD\t\tOne Pair, Eight and High Card, Ten\n" +
                    "\t2\tPlayer 1\t5H 5C 6S 7S KD\t\tOne Pair, Five and High Card, King\n" +
                    "\t3\tPlayer 3\t5D 8C 9S JS AC\t\tHigh Card, Ace\n" +
                    "\n" +
                    "Player 2 wins.\n",
            "5D 8C 9S JS AC,2C 5C 7D 8S QH,2C 5C 7D 8S KH",
            "\nRanking:\n" +
                    "\t1\tPlayer 1\t5D 8C 9S JS AC\t\tHigh Card, Ace\n" +
                    "\t2\tPlayer 3\t2C 5C 7D 8S KH\t\tHigh Card, King\n" +
                    "\t3\tPlayer 2\t2C 5C 7D 8S QH\t\tHigh Card, Queen\n" +
                    "\n" +
                    "Player 1 wins.\n",
            "2D 9C AS AH AC,3D 6D 7D TD QD,2C 5C 7D 8S QH",
            "\nRanking:\n" +
                    "\t1\tPlayer 2\t3D 6D 7D TD QD\t\tFlush, Diamonds\n" +
                    "\t2\tPlayer 1\t2D 9C AS AH AC\t\tThree Of a Kind, Ace\n" +
                    "\t3\tPlayer 3\t2C 5C 7D 8S QH\t\tHigh Card, Queen\n" +
                    "\n" +
                    "Player 2 wins.\n",
            "4D 6S 9H QH QC,3D 6D 7H QD QS,2C 5C 7D 8S QH",
            "\nRanking:\n" +
                    "\t1\tPlayer 1\t4D 6S 9H QH QC\t\tOne Pair, Queen and High Card, Nine\n" +
                    "\t2\tPlayer 2\t3D 6D 7H QD QS\t\tOne Pair, Queen and High Card, Seven\n" +
                    "\t3\tPlayer 3\t2C 5C 7D 8S QH\t\tHigh Card, Queen\n" +
                    "\n" +
                    "Player 1 wins.\n",
            "2H 2D 4C 4D 4S,3C 3D 3S 9S 9D,2C 5C 7D 8S QH",
            "\nRanking:\n" +
                    "\t1\tPlayer 1\t2H 2D 4C 4D 4S\t\tFull House, Four\n" +
                    "\t2\tPlayer 2\t3C 3D 3S 9S 9D\t\tFull House, Three\n" +
                    "\t3\tPlayer 3\t2C 5C 7D 8S QH\t\tHigh Card, Queen\n" +
                    "\n" +
                    "Player 1 wins.\n",
            "2D 9C AS AH AC,3D 6D 7D TD QD,2C 5C 7C 8S QH",
            "\nRanking:\n" +
                    "\t1\tPlayer 2\t3D 6D 7D TD QD\t\tFlush, Diamonds\n" +
                    "\t2\tPlayer 1\t2D 9C AS AH AC\t\tThree Of a Kind, Ace\n" +
                    "\t3\tPlayer 3\t2C 5C 7C 8S QH\t\tHigh Card, Queen\n" +
                    "\n" +
                    "Player 2 wins.\n",
            "TD JD QD KD AD,9D TD JD QD KD,KC AS AC AD AH",
            "\nRanking:\n" +
                    "\t1\tPlayer 1\tTD JD QD KD AD\t\tRoyal Flush, Diamonds\n" +
                    "\t2\tPlayer 2\t9D TD JD QD KD\t\tStraight Flush, Diamonds and High Card, King\n" +
                    "\t3\tPlayer 3\tKC AS AC AD AH\t\tFour Of a Kind, Ace\n" +
                    "\n" +
                    "Player 1 wins.\n",
            "5D 9C 9S AS AC,3H 4C 5S 6S 7D,3C 4H 5S 6S 7D",
            "\nRanking:\n" +
                    "\t1\tPlayer 2\t3H 4C 5S 6S 7D\t\tStraight, Seven of Diamonds\n" +
                    "\t2\tPlayer 3\t3C 4H 5S 6S 7D\t\tStraight, Seven of Diamonds\n" +
                    "\t3\tPlayer 1\t5D 9C 9S AS AC\t\tTwo Pairs, Nine and Ace and High Card, Five\n" +
                    "\n" +
                    "The pot is splitted equally between players [2, 3].\n",
            "4D 6S 9H QH QC,3D 6D 7H QD QS,2C AC 3D 4S 5H",
            "\nRanking:\n" +
                    "\t1\tPlayer 3\t2C AC 3D 4S 5H\t\tStraight, Five of Hearts\n" +
                    "\t2\tPlayer 1\t4D 6S 9H QH QC\t\tOne Pair, Queen and High Card, Nine\n" +
                    "\t3\tPlayer 2\t3D 6D 7H QD QS\t\tOne Pair, Queen and High Card, Seven\n" +
                    "\n" +
                    "Player 3 wins.\n"
    );

    private static Stream<Arguments> hands() {
        return game.keySet().stream().map(Arguments::of);
    }

    public static String getResult(String hand) {
        return game.get(hand);
    }
}
