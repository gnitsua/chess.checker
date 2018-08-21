package com.example;

import com.chesschecker.input.BoardState;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.is;

public class BoardStateTest {
    @Test
    public void test_EmptyConstructor() {
        HashSet<String> empty = new HashSet<>();
        BoardState sut = new BoardState();

        Assert.assertEquals("White not empty",empty,sut.getWhite());
        Assert.assertEquals("Black not empty",empty,sut.getBlack());
        Assert.assertEquals("Move not empty",empty,sut.getMove());
    }
    @Test
    public void test_flip_black_white() {
        HashSet<String> white_set = new HashSet<>(Arrays.asList("Rf1", "Kg1", "Pf2", "Ph2", "Pg3"));
        HashSet<String> white_set_flipped = new HashSet<>(Arrays.asList("Rf8", "Kg8", "Pf7", "Ph7", "Pg6"));
        HashSet<String> black_set = new HashSet<>(Arrays.asList("Kb8", "Ne8", "Pa7", "Pb7", "Pc7", "Ra5"));
        HashSet<String> black_set_flipped = new HashSet<>(Arrays.asList("Kb1", "Ne1", "Pa2", "Pb2", "Pc2", "Ra4"));
        HashSet<String> move_set = new HashSet<>(Arrays.asList("Rf1"));
        BoardState sut = new BoardState(white_set,black_set,move_set);
        sut.flip_black_white();
        Assert.assertEquals(white_set_flipped,sut.getWhite());
        Assert.assertEquals(black_set_flipped,sut.getBlack());
        Assert.assertEquals(move_set,sut.getMove());

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