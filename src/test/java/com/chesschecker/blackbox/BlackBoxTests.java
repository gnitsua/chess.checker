package com.chesschecker.blackbox;

import com.chesschecker.gamestate.BlackBoardState;
import com.chesschecker.gamestate.BoardState;
import com.chesschecker.gamestate.WhiteBoardState;
import com.chesschecker.input.Board;
import com.chesschecker.input.InputParser;
import com.chesschecker.input.MoveList;
import com.chesschecker.moves.BoardMove;
import com.chesschecker.util.BitBoard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * These tests use actual game data to ensure that the generator produces at least
 * one valid move. In this case we are testing that it returns at least the move
 * that was taken next by a real player which we assume is valid.
 */
@SuppressWarnings("ALL")
@RunWith(Parameterized.class)
public class BlackBoxTests {
    @Parameterized.Parameter(value = 0)
    public String input;
    @Parameterized.Parameter(value = 1)
    public String expected;

    @Parameterized.Parameters(name = "{index}: MoveList {0}, Move {1}")
    public static Collection<Object[]> data() {
        BitBoard empty = new Board();
        ArrayList<Object[]> data = new ArrayList();


        try {
            BufferedReader in =  new BufferedReader(new InputStreamReader(BlackBoxTests.class.getClassLoader().getResourceAsStream("test_moves.txt")));
            while (true) {
                String input = in.readLine()+"\n"+in.readLine()+"\n"+in.readLine()+"\n";
                String expected = in.readLine();
                if(expected == null) {
                    break;
                }
                data.add(new String[]{input,expected});
            }

        } catch (IOException e) {
            Assert.fail("Problem reading test input");
        }
//        catch (NullPointerException e) {
//            Assert.fail("Problem reading test input");
//        }

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
                Assert.assertEquals(true, moveList.toString().contains(this.expected));
            } catch (AssertionError e ) {
                System.out.println("---------------Failure-----------------");
                System.out.println(input);
                System.out.println("Expected: " + this.expected);
                System.out.println("Actual: " + moveList.toString());
                throw e;
            }
        } catch (IOException e) {
            Assert.fail("InputParser threw and I/O error");
        }


    }
}
