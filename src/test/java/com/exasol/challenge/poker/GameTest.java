package com.exasol.challenge.poker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

class GameTest extends Result {

    private final ByteArrayOutputStream systemOutStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream systemErrStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(systemOutStream));
        System.setErr(new PrintStream(systemErrStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @ParameterizedTest
    @EmptySource
    void testEmptyHandGame(String... hand) {
        Game.main(hand);
        assertThat(systemErrStream.toString().trim(), equalTo(Game.error("Invalid Hand", hand).trim()));
        assertThat(systemOutStream.toString().trim(), equalTo(Game.usage().trim()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5H 5H 6S 7S KD,2C 3S 8S 8D TD,5D 8C 9S JS AC"})
    void testDuplicateCardsHandGame(String hand) {
        Game.main(hand.split(","));
        assertThat(systemErrStream.toString().trim(), startsWith("Invalid Hand: duplicates are not allowed"));
        assertThat(systemOutStream.toString().trim(), equalTo(Game.usage().trim()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"4H 5H 6S 7S KD 9D,2C 3S 8S 8D TD,5D 8C 9S JS AC"})
    void testFiveCardsHandGameOnly(String hand) {
        Game.main(hand.split(","));
        assertThat(systemErrStream.toString().trim(), startsWith("Invalid Hand: more than 5 cards are not allowed"));
        assertThat(systemOutStream.toString().trim(), equalTo(Game.usage().trim()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10H 5H 6S 7S KD,2C 3S 8S 8D TD,5D 8C 9S JS AC"})
    void testInvalidCardGame(String hand) {
        Game.main(hand.split(","));
        assertThat(systemErrStream.toString().trim(), equalTo("Invalid Card: '10H'."));
        assertThat(systemOutStream.toString().trim(), equalTo(Game.usage().trim()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1H 5H 6S 7S KD,2C 3S 8S 8D TD,5D 8C 9S JS AC"})
    void testInvalidCardRankGame(String hand) {
        Game.main(hand.split(","));
        assertThat(systemErrStream.toString().trim(), equalTo("Invalid Card Rank: '1'."));
        assertThat(systemOutStream.toString().trim(), equalTo(Game.usage().trim()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2Q 5H 6S 7S KD,2C 3S 8S 8D TD,5D 8C 9S JS AC"})
    void testInvalidCardSuitGame(String hand) {
        Game.main(hand.split(","));
        assertThat(systemErrStream.toString().trim(), equalTo("Invalid Card Suit: 'Q'."));
        assertThat(systemOutStream.toString().trim(), equalTo(Game.usage().trim()));
    }

    @ParameterizedTest
    @MethodSource("hands")
    void testGames(String hand) {
        Game.main(hand.split(","));
        assertThat(systemErrStream.toString().trim(), is(emptyString()));
        assertThat(systemOutStream.toString().trim(), equalTo(getResult(hand).trim()));
    }
}