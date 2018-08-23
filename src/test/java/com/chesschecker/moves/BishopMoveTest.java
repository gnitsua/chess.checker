package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class BishopMoveTest {

    @Test
    public void isValid() {
        BitBoard empty = new BitBoard();
        BishopMove sut = new BishopMove(3,4,6,7);
        Assert.assertEquals(true, sut.isValid(empty, empty));
    }

    @Test
    public void isValid2() {
        BitBoard empty = new BitBoard();
        BishopMove sut = new BishopMove(3,4,0,7);
        Assert.assertEquals(true, sut.isValid(empty, empty));
    }

    @Test
    public void isValid3() {
        BitBoard empty = new BitBoard();
        BishopMove sut = new BishopMove(3,4,7,0);
        Assert.assertEquals(true, sut.isValid(empty, empty));
    }

    @Test
    public void isValid4() {
        BitBoard empty = new BitBoard();
        BishopMove sut = new BishopMove(3,4,0,1);
        Assert.assertEquals(true, sut.isValid(empty, empty));
    }

    @Test
    public void testToString() {
        BishopMove sut = new BishopMove(3,4,0,1);
        Assert.assertEquals("Bb1", sut.toString());
    }
}