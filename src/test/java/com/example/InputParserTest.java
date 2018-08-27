package com.example;

import com.chesschecker.input.BoardState;
import com.chesschecker.input.WhiteBoardState;
import com.chesschecker.input.InputParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;

public class InputParserTest {

    @Test
    public void test_parseInput() {
        final HashSet<String> whiteSet = new HashSet<>(Arrays.asList("Rf1", "Kg1", "Pf2", "Ph2", "Pg3"));
        final HashSet<String> blackSet = new HashSet<>(Arrays.asList("Kb8", "Ne8", "Pa7", "Pb7", "Pc7", "Ra5"));
        final HashSet<String> moveSet = new HashSet<>(Arrays.asList("Rf1"));
        final String expectedOutput = "WHITE: BLACK: PIECE TO MOVE: ";
        String inputString = "Rf1, Kg1, Pf2, Ph2, Pg3\nKb8, Ne8, Pa7, Pb7, Pc7, Ra5\nRf1\n";
        InputStream mockInput = new java.io.ByteArrayInputStream(inputString.getBytes());

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream mockOutput = new PrintStream(baos);
        InputParser sut = new InputParser(mockInput, mockOutput);

        try {// TODO: test throwing the exception
            BoardState test = sut.parseInput();
            Assert.assertEquals(test.getWhite(), whiteSet);
            Assert.assertEquals(test.getBlack(), blackSet);
            Assert.assertEquals(expectedOutput,baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}