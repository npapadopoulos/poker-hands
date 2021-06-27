package com.exasol.challenge.poker.evaluators;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;
import com.exasol.challenge.poker.hands.FlushHand;
import com.exasol.challenge.poker.hands.Hand;
import com.exasol.challenge.poker.hands.HandRank;
import com.exasol.challenge.poker.hands.HighCardHand;
import com.exasol.challenge.poker.hands.RoyalFlushHand;
import com.exasol.challenge.poker.hands.StraightHand;

import java.util.Optional;

import static java.util.Optional.of;

/**
 * A class implementing the {@link Evaluator} to evaluate {@link Card}s against {@link RoyalFlushHand}.
 */
public class RoyalFlushEvaluator extends Evaluator {

    /**
     * Checks if the provided array of {@link Card} was already evaluated and present in the cache,
     * against {@link StraightHand} with {@link HighCardHand} of {@link CardRank#ACE}
     * and {@link FlushHand}. If true, constructs and returns
     * the {@link RoyalFlushHand}, otherwise calls the next {@link Evaluator} to process the array of {@link Card}.
     */
    @Override
    public Optional<Hand> evaluate(Card[] cards) {
        var flush = StraightEvaluator.get(cards, HandRank.STRAIGHT);
        var straight = FlushEvaluator.get(cards, HandRank.FLUSH);
        if (flush.isPresent() && straight.isPresent() && cards[cards.length - 1].getRank() == CardRank.ACE) {
            return of(new RoyalFlushHand(getHandRank(), cards));
        }
        return evaluateNext(cards);
    }

    @Override
    public HandRank getHandRank() {
        return HandRank.ROYAL_FLUSH;
    }
}
