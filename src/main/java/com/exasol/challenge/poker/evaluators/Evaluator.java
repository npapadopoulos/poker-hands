package com.exasol.challenge.poker.evaluators;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;
import com.exasol.challenge.poker.cards.CardSuit;
import com.exasol.challenge.poker.hands.Hand;
import com.exasol.challenge.poker.hands.HandRank;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * Base class that allows passing an array of {@link Card} along
 * the chain of evaluators until one of them handles the specified hand.
 * <p>
 * Note, that {@link HighCardEvaluator} is always applied.
 *
 * @see HighCardEvaluator
 * @see RoyalFlushEvaluator
 * @see StraightFlushEvaluator
 * @see FourOfKindEvaluator
 * @see FullHouseEvaluator
 * @see FlushEvaluator
 * @see StraightEvaluator
 * @see TwoPairsEvaluator
 * @see OnePairEvaluator
 */
public abstract class Evaluator {

    private static final SortedMap<HandRank, Hand> handRanks = new TreeMap<>();
    private static Map<CardRank, List<CardSuit>> cardsRanks;
    private Evaluator next;

    /**
     * Returns a {@link SortedMap} containing an evaluated {@link Hand} for specific {@link HandRank}.
     * <p>
     * Note, that this map will always have a {@link Hand} value for {@link HandRank#HIGH_CARD} as it is being used for determining the helper card
     * in case of equal {@link Hand}s.
     */
    static SortedMap<HandRank, Hand> getHandRanks() {
        return handRanks;
    }

    /**
     * Calculates only once the frequencies of the suit by {@link CardRank}.
     */
    static Map<CardRank, List<CardSuit>> getCardsRanks(Card[] cards) {
        return cardsRanks == null ? cardsRanks = Arrays.stream(cards).collect(groupingBy(Card::getRank, mapping(Card::getSuit, toList()))) : cardsRanks;
    }

    /**
     * Clears and resets the hand and card ranks caches in order to evaluate the next hand.
     */
    public static void reset() {
        handRanks.clear();
        cardsRanks = null;
    }

    /**
     * Builds chains of {@link Evaluator} objects.
     */
    public Evaluator add(Evaluator next) {
        this.next = next;
        return next;
    }

    /**
     * Evaluates the specified array of {@link Card} and returns a {@link Hand}.
     */
    public abstract Optional<Hand> evaluate(Card[] cards);

    /**
     * Returns {@link HandRank}.
     */
    public abstract HandRank getHandRank();

    /**
     * Checks the next {@link Evaluator} in chain or ends traversing by returning the evaluated {@link Hand} if we're in
     * last {@link Evaluator} in chain.
     */
    protected Optional<Hand> evaluateNext(Card[] cards) {
        return next.evaluate(cards);
    }
}
