package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class SlideMoveTest {

    @Test
    public void isValidHorizontal() {
        BitBoard empty = new BitBoard();
        SlideMove sut = new SlideMove(0,0,7,0);
        Assert.assertEquals(true,((BoardMove)sut).isValid(empty,empty));
        Assert.assertEquals(true,((ColoredMove)sut).isValid(empty,empty));
        Assert.assertEquals(true,sut.isValid(empty,empty));
    }

    @Test
    public void isValidVertical() {
        BitBoard empty = new BitBoard();
        SlideMove sut = new SlideMove(0,0,0,7);
        Assert.assertEquals(true,((BoardMove)sut).isValid(empty,empty));
        Assert.assertEquals(true,((ColoredMove)sut).isValid(empty,empty));
        Assert.assertEquals(true,sut.isValid(empty,empty));
    }

    @Test
    public void isValidDiagonal1() {
        BitBoard empty = new BitBoard();
        SlideMove sut = new SlideMove(0,0,7,7);
        Assert.assertEquals(true,((BoardMove)sut).isValid(empty,empty));
        Assert.assertEquals(true,((ColoredMove)sut).isValid(empty,empty));
        Assert.assertEquals(true,sut.isValid(empty,empty));
    }

    @Test
    public void isValidDiagonal2() {
        BitBoard empty = new BitBoard();
        SlideMove sut = new SlideMove(0,7,7,0);
        Assert.assertEquals(true,((BoardMove)sut).isValid(empty,empty));
        Assert.assertEquals(true,((ColoredMove)sut).isValid(empty,empty));
        Assert.assertEquals(true,sut.isValid(empty,empty));
    }

    @Test
    public void isNotValidHorizontal() {
        BitBoard empty = new BitBoard();
        BitBoard friendly = new BitBoard();
        friendly.setOccupancy(4,0);
        SlideMove sut = new SlideMove(0,0,7,0);
//        Assert.assertEquals(true,((BoardMove)sut).isValid(friendly,empty)); // TODO: come up with a way to check super call
//        Assert.assertEquals(true,((ColoredMove)sut).isValid(friendly,empty));
        Assert.assertEquals(false,sut.isValid(friendly,empty));//check to make sure both friendly and foes block move
        Assert.assertEquals(false,sut.isValid(friendly,friendly));
        Assert.assertEquals(false,sut.isValid(empty,friendly));
    }

    @Test
    public void isNotValidVertical() {
        BitBoard empty = new BitBoard();
        BitBoard friendly = new BitBoard();
        friendly.setOccupancy(0,4);
        SlideMove sut = new SlideMove(0,0,0,7);
//        Assert.assertEquals(true,((BoardMove)sut).isValid(friendly,empty));
//        Assert.assertEquals(true,((ColoredMove)sut).isValid(friendly,empty));
        Assert.assertEquals(false,sut.isValid(friendly,empty));
        Assert.assertEquals(false,sut.isValid(friendly,friendly));
        Assert.assertEquals(false,sut.isValid(empty,friendly));
    }

    @Test
    public void isNotValidDiagonal1() {
        BitBoard empty = new BitBoard();
        BitBoard friendly = new BitBoard();
        friendly.setOccupancy(4,4);
        SlideMove sut = new SlideMove(0,0,7,7);
//        Assert.assertEquals(true,((BoardMove)sut).isValid(friendly,empty));
//        Assert.assertEquals(true,((ColoredMove)sut).isValid(friendly,empty));
        Assert.assertEquals(false,sut.isValid(friendly,empty));
        Assert.assertEquals(false,sut.isValid(friendly,friendly));
        Assert.assertEquals(false,sut.isValid(empty,friendly));
    }

    @Test
    public void isNotValidDiagonal2() {
        BitBoard empty = new BitBoard();
        BitBoard friendly = new BitBoard();
        friendly.setOccupancy(3,4);
        SlideMove sut = new SlideMove(0,7,7,0);
//        Assert.assertEquals(true,((BoardMove)sut).isValid(friendly,empty));
//        Assert.assertEquals(true,((ColoredMove)sut).isValid(friendly,empty));
        Assert.assertEquals(false,sut.isValid(friendly,empty));
        Assert.assertEquals(false,sut.isValid(friendly,friendly));
        Assert.assertEquals(false,sut.isValid(empty,friendly));
    }

    @Test
    public void getPassedThroughSquares() {
        BitBoard expectectOutput = new BitBoard();
        expectectOutput.setOccupancy(1,1);
        expectectOutput.setOccupancy(2,2);
        expectectOutput.setOccupancy(3,3);
        expectectOutput.setOccupancy(4,4);
        expectectOutput.setOccupancy(5,5);
        expectectOutput.setOccupancy(6,6);

        SlideMove sut = new SlideMove(0,0,7,7);
        Assert.assertEquals(expectectOutput,sut.getPassedThroughSquares());
    }

    @Test
    public void getPassedThroughSquares_2() {
        BitBoard expectectOutput = new BitBoard();
        expectectOutput.setOccupancy(1,6);
        expectectOutput.setOccupancy(2,5);
        expectectOutput.setOccupancy(3,4);
        expectectOutput.setOccupancy(4,3);
        expectectOutput.setOccupancy(5,2);
        expectectOutput.setOccupancy(6,1);

        SlideMove sut = new SlideMove(0,7,7,0);
        Assert.assertEquals(expectectOutput,sut.getPassedThroughSquares());
    }

    @Test
    public void getPassedThroughSquares_4() {
        BitBoard expectectOutput = new BitBoard();
        expectectOutput.setOccupancy(0,6);
        expectectOutput.setOccupancy(0,5);
        expectectOutput.setOccupancy(0,4);
        expectectOutput.setOccupancy(0,3);
        expectectOutput.setOccupancy(0,2);
        expectectOutput.setOccupancy(0,1);

        SlideMove sut = new SlideMove(0,0,0,7);
        Assert.assertEquals(expectectOutput,sut.getPassedThroughSquares());
    }

    @Test
    public void getPassedThroughSquares_5() {
        BitBoard expectectOutput = new BitBoard();
        expectectOutput.setOccupancy(7,6);
        expectectOutput.setOccupancy(7,5);
        expectectOutput.setOccupancy(7,4);
        expectectOutput.setOccupancy(7,3);
        expectectOutput.setOccupancy(7,2);
        expectectOutput.setOccupancy(7,1);

        SlideMove sut = new SlideMove(7,0,7,7);
        Assert.assertEquals(expectectOutput,sut.getPassedThroughSquares());
    }

    @Test
    public void getPassedThroughSquares_6() {
        BitBoard expectectOutput = new BitBoard();
        expectectOutput.setOccupancy(6,0);
        expectectOutput.setOccupancy(5,0);
        expectectOutput.setOccupancy(4,0);
        expectectOutput.setOccupancy(3,0);
        expectectOutput.setOccupancy(2,0);
        expectectOutput.setOccupancy(1,0);

        SlideMove sut = new SlideMove(0,0,7,0);
        Assert.assertEquals(expectectOutput,sut.getPassedThroughSquares());
    }

    @Test
    public void getPassedThroughSquares_7() {
        BitBoard expectectOutput = new BitBoard();
        expectectOutput.setOccupancy(6,7);
        expectectOutput.setOccupancy(5,7);
        expectectOutput.setOccupancy(4,7);
        expectectOutput.setOccupancy(3,7);
        expectectOutput.setOccupancy(2,7);
        expectectOutput.setOccupancy(1,7);
        SlideMove sut = new SlideMove(0,7,7,7);
        Assert.assertEquals(expectectOutput,sut.getPassedThroughSquares());
    }

    /**
     * Test not moving
     */
    @Test
    public void getPassedThroughSquares_8() {
        BitBoard expectectOutput = new BitBoard();
        SlideMove sut = new SlideMove(7,7,7,7);
        Assert.assertEquals(expectectOutput,sut.getPassedThroughSquares());
    }

    /**
     * Test only moving one square (from experience this can be tricky)
     */
    @Test
    public void getPassedThroughSquares_9() {
        BitBoard expectectOutput = new BitBoard();
        SlideMove sut = new SlideMove(6,6,7,7);
        Assert.assertEquals(expectectOutput,sut.getPassedThroughSquares());
    }
}