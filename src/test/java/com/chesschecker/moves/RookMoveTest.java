package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ALL")
public class RookMoveTest {

    @Test
    public void isValidHorizontal1() {
        BitBoard empty = new BitBoard();
        RookMove sut = new RookMove(2, 3, 2, 7);
        Assert.assertEquals(true, sut.isValid(empty, empty));
    }

    @Test
    public void isValidVertical() {
        BitBoard empty = new BitBoard();
        RookMove sut = new RookMove(2, 3, 7, 3);
        Assert.assertEquals(true, sut.isValid(empty, empty));
    }

    @Test
    public void isNotValidVertical() {
        BitBoard empty = new BitBoard();
        RookMove sut = new RookMove(2, 3, 7, 7);
        Assert.assertEquals(false, sut.isValid(empty, empty));
    }

    @Test
    public void testToString() {
        RookMove sut = new RookMove(2,3,7,7);
        Assert.assertEquals("Rh8", sut.toString());
    }
}