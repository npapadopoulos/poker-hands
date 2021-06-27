package com.exasol.challenge.poker.evaluators;

import com.exasol.challenge.poker.hands.HandRank;

public class TwoPairsEvaluator extends AbstractPairEvaluator {
    @Override
    public HandRank getHandRank() {
        return HandRank.TWO_PAIRS;
    }
}
