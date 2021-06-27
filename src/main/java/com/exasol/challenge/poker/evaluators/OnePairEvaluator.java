package com.exasol.challenge.poker.evaluators;

import com.exasol.challenge.poker.hands.HandRank;

public class OnePairEvaluator extends AbstractPairEvaluator {
    @Override
    public HandRank getHandRank() {
        return HandRank.ONE_PAIR;
    }
}
