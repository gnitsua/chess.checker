package com.example;

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
    public void test_toString() {
        String testString = "[a, b, c]\n[a, b, c]\n[a, b, c]";
        String[] set = {"a", "b", "c"};
        HashSet<String> white = new HashSet<String>(Arrays.asList(set));
        HashSet<String> black = new HashSet<String>(Arrays.asList(set));
        HashSet<String> move = new HashSet<String>(Arrays.asList(set));
        BoardState sut = new BoardState(white, black, move);

        Assert.assertEquals(testString, sut.toString());
    }
}