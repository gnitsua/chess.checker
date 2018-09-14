package com.chesschecker.blackbox;

import com.chesschecker.gamestate.BlackBoardState;
import com.chesschecker.gamestate.BoardState;
import com.chesschecker.gamestate.WhiteBoardState;
import com.chesschecker.input.InputParser;
import com.chesschecker.input.MoveList;
import com.chesschecker.moves.BoardMove;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Stream;

/**
 * These tests use actual game data to ensure that the generator produces at least
 * one valid move. In this case we are testing that it returns at least the move
 * that was taken next by a real player which we assume is valid.
 */
@SuppressWarnings("ALL")
public class BlackBoxTests {
    @Test
    public void testGroundTruth(){
        BufferedReader in =  new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("test_moves.txt")));

        int tests = 0;
        int failures = 0;
        try {

            while (true) {
                String input = in.readLine()+"\n"+in.readLine()+"\n"+in.readLine()+"\n";
                String expected = in.readLine();
                if(expected == null) {
                    break;
                }
                InputStream targetStream = new ByteArrayInputStream(input.getBytes());
                ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                InputParser sut = new InputParser(targetStream,outContent);
                BoardState state = sut.parseInput();

                Assert.assertEquals("WHITE: BLACK: PIECE TO MOVE: ",outContent.toString());
                Set<BoardMove> moveList = state.getValidMoves();
                try {
                    Assert.assertEquals(true, moveList.toString().contains(expected));
                } catch (AssertionError e ) {
                    failures++;
                    System.out.println("---------------Failure-----------------");
                    System.out.println(input);
                    System.out.println("Expected: " + expected);
                    System.out.println("Actual: " + moveList.toString());
                }
                tests++;
            }

        } catch (IOException e) {
            Assert.fail("Problem reading test input");
        }
        Assert.assertEquals(failures + " out of " + tests + " failed",0, failures);
    }
}
