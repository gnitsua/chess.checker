package com.chesschecker.moves;

import com.chesschecker.gamestate.BoardState;
import com.chesschecker.input.Board;
import com.chesschecker.input.InputParser;
import com.chesschecker.util.BitBoard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * These tests use actual game data to ensure that the generator produces at least
 * one valid move. In this case we are testing that it returns at least the move
 * that was taken next by a real player which we assume is valid.
 */
@SuppressWarnings("ALL")
@RunWith(Parameterized.class)
public class CheckTests {
    @Parameterized.Parameter(value = 0)
    public String input;
    @Parameterized.Parameter(value = 1)
    public String expected;

    @Parameterized.Parameters(name = "{index}: MoveList {0}, Move {1}")
    public static Collection<Object[]> data() {
        BitBoard empty = new Board();
        ArrayList<Object[]> data = new ArrayList();

        data.add(new String[]{"Ke1, Rd2\nRe8\nRd2\n","Rd3"});//White is pinned (not currently blocking check)
        data.add(new String[]{"Ke1, Re2\nRe8\nRe2\n","Rf2"});//White is pinned
        data.add(new String[]{"Ke1, Rf2\nRe8\nKe1\n","Ke2"});//King can't move into check

        return data;
    }

    @Test
    public void testGroundTruth(){
        InputStream targetStream = new ByteArrayInputStream(this.input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        InputParser sut = new InputParser(targetStream,outContent);
        BoardState state = null;
        try {
            state = sut.parseInput();
            Assert.assertEquals("WHITE: BLACK: PIECE TO MOVE: ",outContent.toString());
            Set<BoardMove> moveList = state.getValidMoves();
            try {
                Assert.assertEquals(false, moveList.toString().contains(this.expected));
            } catch (AssertionError e ) {
                System.out.println("---------------Failure-----------------");
                System.out.println(input);
                System.out.println("Invalid Move: " + this.expected);
                System.out.println("Proposed Valid Move: " + moveList.toString());
                throw e;
            }
        } catch (IOException e) {
            Assert.fail("InputParser threw and I/O error");
        }


    }
}
