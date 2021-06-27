package com.exasol.challenge.poker.evaluators;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;
import com.exasol.challenge.poker.hands.Hand;
import com.exasol.challenge.poker.hands.HandRank;
import com.exasol.challenge.poker.hands.StraightHand;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

/**
 * A class implementing the {@link Evaluator} to evaluate {@link Card}s against {@link StraightHand}.
 */
public class StraightEvaluator extends Evaluator {

    /**
     * Checks if the provided array of {@link Card} was already evaluated and present in the cache, against {@link StraightHand}.
     * <p>
     * If true, returns the already evaluated {@link StraightHand}, otherwise evaluates the provided array of {@link Card} against the {@link HandRank#STRAIGHT}.
     * <p>
     * The process of evaluation ends if there are sequential {@link CardRank} value order available for all cards and returns {@link Optional#empty()},
     * otherwise constructs and returns the {@link StraightHand}.
     */
    static Optional<Hand> get(Card[] cards, HandRank rank) {
        var processedHand = ofNullable(Evaluator.getHandRanks().get(rank));
        if (processedHand.isPresent()) {
            return processedHand;
        }
        for (var i = 1; i < cards.length; i++) {
            if (cards[i - 1].getRank().getValue() + 1 != cards[i].getRank().getValue()) {
                if (!(i == (cards.length - 1) && cards[0].getRank() == CardRank.TWO && cards[i].getRank() == CardRank.ACE)) {
                    return empty();
                }
            }
        }

        var hand = new StraightHand(rank, cards);
        Evaluator.getHandRanks().put(rank, hand);
        return of(hand);
    }

    /**
     * Returns the evaluated {@link StraightHand}, if present, otherwise calls the next {@link Evaluator} to process the array of {@link Card}.
     */
    @Override
    public Optional<Hand> evaluate(Card[] cards) {
        return get(cards, getHandRank()).or(() -> evaluateNext(cards));
    }

    @Override
    public HandRank getHandRank() {
        return HandRank.STRAIGHT;
    }
}