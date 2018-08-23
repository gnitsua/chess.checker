package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ALL")
public class ColoredMoveTest {

    /**
     * Ensure that a move is valid iff it is a valid board move as well as a valid move
     * for the entire heiracy
     */
    @Test
    public void isValid() {
        final BitBoard empty = new BitBoard();
        ColoredMove sut = new ColoredMove(0,0,4,4);
        BoardMove sutSuper = new BoardMove(0,0,4,4);
        Assert.assertEquals(sutSuper.isValid(empty,empty), true);
        Assert.assertEquals(sut.isValid(empty,empty), true);

    }

    /**
     * This test ensure that it is valid for a peice to move (or not move) to its own square
     * We are making the assumption that the only way for the start of a move to have a friendly
     * piece in it is if that peice is the one making the move.
     */
    @Test
    public void isValid_2() {
        final BitBoard empty = new BitBoard();
        final BitBoard friendly = new BitBoard();
        friendly.setOccupancy(4,4);
        ColoredMove sut = new ColoredMove(4,4,4,4);
        BoardMove sutSuper = new BoardMove(4,4,4,4);
        Assert.assertEquals(sutSuper.isValid(empty,empty), true);
        Assert.assertEquals(sut.isValid(friendly,empty), true);
    }

    @Test
    public void isNotValid() {
        final BitBoard empty = new BitBoard();
        final BitBoard friendly = new BitBoard();
        friendly.setOccupancy(4,4);
        ColoredMove sut = new ColoredMove(0,0,4,4);
        BoardMove sutSuper = new BoardMove(0,0,4,4);
        Assert.assertEquals(sutSuper.isValid(friendly,empty), true);
        Assert.assertEquals(sut.isValid(friendly,empty), false);
    }
}