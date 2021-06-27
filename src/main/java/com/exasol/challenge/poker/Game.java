package com.exasol.challenge.poker;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;
import com.exasol.challenge.poker.cards.CardSuit;
import com.exasol.challenge.poker.evaluators.Evaluator;
import com.exasol.challenge.poker.evaluators.FlushEvaluator;
import com.exasol.challenge.poker.evaluators.FourOfKindEvaluator;
import com.exasol.challenge.poker.evaluators.FullHouseEvaluator;
import com.exasol.challenge.poker.evaluators.HighCardEvaluator;
import com.exasol.challenge.poker.evaluators.OnePairEvaluator;
import com.exasol.challenge.poker.evaluators.RoyalFlushEvaluator;
import com.exasol.challenge.poker.evaluators.StraightEvaluator;
import com.exasol.challenge.poker.evaluators.StraightFlushEvaluator;
import com.exasol.challenge.poker.evaluators.ThreeOfKindEvaluator;
import com.exasol.challenge.poker.evaluators.TwoPairsEvaluator;
import com.exasol.challenge.poker.exceptions.InvalidCardException;
import com.exasol.challenge.poker.hands.Hand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Game {

    public static void main(String... args) {
        processHands(args).ifPresent(Game::print);
    }

    static Optional<List<Hand>> processHands(String... args) {
        if (args.length == 0) {
            System.err.println(error("Invalid Hand", args));
            System.out.println(usage());
            return Optional.empty();
        }
        Evaluator evaluator = registerEvaluators();
        List<Hand> hands = new ArrayList<>();
        for (var i = 0; i < args.length; i++) {
            var cards = Arrays.stream(args[i].split(" ")).toArray(String[]::new);
            if (!isValid(cards)) {
                return Optional.empty();
            }
            Hand hand;
            try {
                //sort the cards and the pass to first evaluator in the chain and get the resulted Hand
                hand = evaluator.evaluate(Arrays.stream(cards).map(Card::new).sorted().toArray(Card[]::new)).get();
            } catch (InvalidCardException e) {
                System.err.println("\n" + e.getMessage());
                System.out.println(usage());
                return Optional.empty();
            } finally {
                //prepare ranks for next hand
                Evaluator.reset();
            }
            hand.setRawCards(args[i]); // set the raw cards input, to use it in the result printing
            hand.setId(i + 1); // set the player id, to use it in the result printing
            hands.add(hand);
        }

        return Optional.of(hands.stream().sorted(comparing(Hand::getValue).reversed()).collect(Collectors.toList()));
    }

    /**
     * Initializes all {@link Evaluator}s.
     */
    private static Evaluator registerEvaluators() {
        Evaluator evaluator = new RoyalFlushEvaluator();
        evaluator.add(new StraightFlushEvaluator())
                .add(new FourOfKindEvaluator())
                .add(new FullHouseEvaluator())
                .add(new FlushEvaluator())
                .add(new StraightEvaluator())
                .add(new ThreeOfKindEvaluator())
                .add(new OnePairEvaluator())
                .add(new TwoPairsEvaluator())
                .add(new HighCardEvaluator());
        return evaluator;
    }

    static boolean isValid(String... cards) {
        try {
            var size = Set.of(cards).size();
            if (size > 5) {
                System.err.println(error("Invalid Hand: more than 5 cards are not allowed", cards));
                System.out.println(usage());
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(error("Invalid Hand: duplicates are not allowed", cards));
            System.out.println(usage());
            return false;
        }
        return true;
    }

    static void printWinners(List<Hand> hands) {
        List<Integer> handsIdsWithHighestValue = checkForDraw(hands);
        if (!handsIdsWithHighestValue.isEmpty()) {
            System.out.println("\nThe pot is splitted equally between players " + handsIdsWithHighestValue + ".\n");
        } else {
            var first = hands.iterator().next();
            System.out.println("\nPlayer " + first.getId() + " wins.\n");
        }
    }

    static void print(List<Hand> hands) {
        printRanking(hands);
        printWinners(hands);
    }

    static void printRanking(List<Hand> hands) {
        var index = new AtomicInteger(1);
        hands.forEach(hand -> {
            if (index.get() == 1) {
                System.out.println("\nRanking:");
            }
            var output = "\t" +
                    index +
                    "\tPlayer " +
                    hand.getId() +
                    "\t" +
                    hand.getRawCards() +
                    "\t\t" + hand;
            System.out.println(output);
            index.getAndIncrement();
        });
    }

    static List<Integer> checkForDraw(List<Hand> hands) {
        var handsValuesById = hands.stream().collect(toMap(Hand::getId, Hand::getValue));
        var handsIdsByValue = handsValuesById
                .entrySet()
                .stream()
                .collect(groupingBy(Map.Entry::getValue, mapping(Map.Entry::getKey, toList())));
        return handsIdsByValue
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .stream()
                .filter(e -> e.getValue().size() > 1)
                .flatMap(e -> e.getValue().stream())
                .collect(toList());
    }

    static String usage() {
        return String.format("Usage: java -jar poker-hands.jar \"RS RS RS RS RS\" \".. .. ..\" ... \n" +
                        "\tR: Card Rank, one of %s\n\tS: Card Suit, one of %s\n",
                Arrays.toString(CardRank.symbols()), Arrays.toString(CardSuit.symbols()));
    }

    static String error(String message, String... args) {
        return String.format("\n%s: %s", message, Arrays.toString(args));
    }
}
