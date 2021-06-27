package com.exasol.challenge.poker.hands;

import com.exasol.challenge.poker.cards.Card;
import com.exasol.challenge.poker.cards.CardRank;
import com.exasol.challenge.poker.cards.CardSuit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

import static com.exasol.challenge.poker.hands.HandRank.BASE_VALUE;
import static com.exasol.challenge.poker.hands.HandRank.HIGH_CARD;
import static com.exasol.challenge.poker.hands.HandRank.ONE_PAIR;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class PairHand extends Hand {
    private CardRank highestCard;

    public PairHand(HandRank handRank, SortedMap<CardRank, List<CardSuit>> importantCards, Card[] cards) {
        super(handRank, importantCards, cards);
    }

    /**
     * Returns the {@link HandRank#ONE_PAIR} or {@link HandRank#TWO_PAIRS} {@link PairHand}'s value by
     * multiplying the ordinal value of the {@link HandRank) by the {@link HandRank#BASE_VALUE} and
     * adding {@link HandRank#ONE_PAIR} value to {@link CardRank} values length in power of remaining cards size by pair card value
     *                    plus {@link CardRank} values length in power of remaining cards size - 1  by highest card value
     *                    plus {@link CardRank} values length in power of remaining cards size  by middle card value
     *                    plus lowest card value,
     * or adding {@link HandRank#TWO_PAIRS} value to {@link CardRank} values length in power of 2 (due to pair size) by high pair card value
     *                    plus {@link CardRank} values length by low pair card value plus remaining unmatched card.
     */
    @Override
    public double getValue() {
        var value = BASE_VALUE * getHandRank().ordinal();
        var copy = Arrays.stream(cards).map(Card::getRank).collect(Collectors.toList());
        var cardRanks = getImportantCards().keySet().stream().sorted(Comparator.reverseOrder()).collect(toList());
        copy.removeAll(cardRanks);
        highestCard = copy.get(copy.size() - 1);

        if (getHandRank() == ONE_PAIR) {
            var pairCardValue = cardRanks.iterator().next().getValue();
            return value + Math.pow(CardRank.values().length, 3) * pairCardValue +
                    Math.pow(CardRank.values().length, 2) * copy.get(2).getValue() +
                    CardRank.values().length * copy.get(1).getValue() +
                    copy.get(0).getValue();
        }

        return value + Math.pow(CardRank.values().length, 2) * cardRanks.get(1).getValue() +
                CardRank.values().length * cardRanks.get(0).getValue() +
                copy.get(0).getValue();
    }

    /**
     * Constructs and returns the printable result of template RANK + PAIR_CARD + HIGH_CARD.
     * eg. One Pair, Queen and High Card, Nine
     */
    @Override
    public String toString() {
        return getHandRank() + ", " + getImportantCards()
                .keySet()
                .stream()
                .map(CardRank::toString)
                .collect(joining(" and ")) + " and " + HIGH_CARD + ", " + highestCard;
    }
}
