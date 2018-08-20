package com.example;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;

public class InputParserTest {

    @Test
    public void test_parseInput() {
        HashSet<String> white_set = new HashSet<>(Arrays.asList("Rf1", "Kg1", "Pf2", "Ph2", "Pg3"));
        HashSet<String> black_set = new HashSet<>(Arrays.asList("Kb8", "Ne8", "Pa7", "Pb7", "Pc7", "Ra5"));
        HashSet<String> move_set = new HashSet<>(Arrays.asList("Rf1"));
        String string = "Rf1, Kg1, Pf2, Ph2, Pg3\nKb8, Ne8, Pa7, Pb7, Pc7, Ra5\nRf1\n";
        InputStream mockInput = new java.io.ByteArrayInputStream(string.getBytes());

        PrintStream mockOutput = new PrintStream(new java.io.ByteArrayOutputStream());
        InputParser sut = new InputParser(mockInput, mockOutput);

        try {// TODO: how should this be tested?
            BoardState test = sut.parseInput();
            Assert.assertEquals(test.getWhite(), white_set);
            Assert.assertEquals(test.getBlack(), black_set);
            Assert.assertEquals(test.getMove(), move_set);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}