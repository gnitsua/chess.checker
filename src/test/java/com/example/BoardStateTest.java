package com.example;

import com.chesschecker.input.BoardState;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class BoardStateTest {
    @Test
    public void test_EmptyConstructor() {
        HashSet<String> empty = new HashSet<>();
        BoardState sut = new BoardState();

        Assert.assertEquals("White not empty", empty, sut.getWhite());
        Assert.assertEquals("Black not empty", empty, sut.getBlack());
        Assert.assertEquals("Move not empty", empty, sut.getMove());
    }

    @Test
    public void test_ForWhiteMoves() {
        HashSet<String> white = new HashSet<String>(Arrays.asList("Rf1", "Kg1", "Pf2", "Ph2", "Pg3"));
        HashSet<String> black = new HashSet<String>(Arrays.asList("Kb8", "Ne8", "Pa7", "Pb7", "Pc7", "Ra5"));
        HashSet<String> move = new HashSet<String>(Arrays.asList("Rf1"));
        BoardState sut = new BoardState(white, black, move);

        Assert.assertEquals("White is not correct", white, sut.getWhite());
        Assert.assertEquals("Black is not correct", black, sut.getBlack());
        Assert.assertEquals("Move in not correct", move, sut.getMove());
    }

    @Test
    public void test_ForBlackMoves() {
        HashSet<String> black = new HashSet<String>(Arrays.asList("Rf1", "Kg1", "Pf2", "Ph2", "Pg3"));
        HashSet<String> white = new HashSet<String>(Arrays.asList("Kb8", "Ne8", "Pa7", "Pb7", "Pc7", "Ra5"));
        HashSet<String> move = new HashSet<String>(Arrays.asList("Rf1"));
        BoardState sut = new BoardState(white, black, move);

        Assert.assertEquals("White is not correct", white, sut.getWhite());
        Assert.assertEquals("Black is not correct", black, sut.getBlack());
        Assert.assertEquals("Move is not correct", move, sut.getMove());
    }

    @Test
    public void test_ForNoValidMoves() {
        HashSet<String> black = new HashSet<String>(Arrays.asList("Rf1", "Kg1", "Pf2", "Ph2", "Pg3"));
        HashSet<String> white = new HashSet<String>(Arrays.asList("Kb8", "Ne8", "Pa7", "Pb7", "Pc7", "Ra5"));
        HashSet<String> move = new HashSet<String>(Arrays.asList("Pf1"));
        HashSet<String> empty = new HashSet<>();
        BoardState sut = new BoardState(white, black, move);

        Assert.assertEquals("White is not correct", empty, sut.getWhite());
        Assert.assertEquals("Black is not correct", empty, sut.getBlack());
        Assert.assertEquals("Move is not correct", empty, sut.getMove());
    }

    @Test
    public void test_ForFilteringKing() {
        HashSet<String> black = new HashSet<String>(Arrays.asList("Rf1", "Kg1", "Pf2", "Ph2", "Pg3"));
        HashSet<String> blackNoKing = new HashSet<String>(Arrays.asList("Rf1", "Pf2", "Ph2", "Pg3"));
        HashSet<String> white = new HashSet<String>(Arrays.asList("Kb8", "Ne8", "Pa7", "Pb7", "Pc7", "Ra5"));
        HashSet<String> whiteNoKing = new HashSet<String>(Arrays.asList("Ne8", "Pa7", "Pb7", "Pc7", "Ra5"));
        HashSet<String> move = new HashSet<String>(Arrays.asList("Rf1"));
        BoardState sut = new BoardState(white, black, move);

        Assert.assertEquals("White is not correct", whiteNoKing, sut.getWhiteWhithoutKing());
        Assert.assertEquals("Black is not correct", blackNoKing, sut.getBlackWhithoutKing());
        Assert.assertEquals("Move is not correct", move, sut.getMove());
    }

    @Test
    public void test_ForNoKing() {
        HashSet<String> black = new HashSet<String>(Arrays.asList("Rf1", "Pf2", "Ph2", "Pg3"));
        HashSet<String> white = new HashSet<String>(Arrays.asList("Ne8", "Pa7", "Pb7", "Pc7", "Ra5"));
        HashSet<String> move = new HashSet<String>(Arrays.asList("Rf1"));
        BoardState sut = new BoardState(white, black, move);

        Assert.assertEquals("White is not correct", white, sut.getWhiteWhithoutKing());
        Assert.assertEquals("Black is not correct", black, sut.getBlackWhithoutKing());
        Assert.assertEquals("Move is not correct", move, sut.getMove());
    }

    @Test
    public void test_toString() {
        String testString = "[a, b, c]\n[a, b, c]\n[a, b, c]\n";
        String[] set = {"a", "b", "c"};
        HashSet<String> white = new HashSet<String>(Arrays.asList(set));
        HashSet<String> black = new HashSet<String>(Arrays.asList(set));
        HashSet<String> move = new HashSet<String>(Arrays.asList(set));
        BoardState sut = new BoardState(white, black, move);

        Assert.assertEquals(testString, sut.toString());
    }
}