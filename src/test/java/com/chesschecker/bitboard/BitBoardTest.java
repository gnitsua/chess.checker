package com.chesschecker.bitboard;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;

public class BitBoardTest {

    @Test
    public void test_setOccupancy_invalid_1() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(-1, 4);
        Assert.assertEquals(Long.parseLong("0"), sut.getState());
    }

    @Test
    public void test_setOccupancy_invalid_2() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(9, 4);
        Assert.assertEquals(Long.parseLong("0"), sut.getState());
    }

    @Test
    public void test_setOccupancy_invalid_3() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(4, -1);
        Assert.assertEquals(Long.parseLong("0"), sut.getState());
    }

    @Test
    public void test_setOccupancy_invalid_4() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(4, 9);
        Assert.assertEquals(Long.parseLong("0"), sut.getState());
    }

    @Test
    public void test_flipVertical() {
        final BitBoard expectedOut = new BitBoard();
        expectedOut.setOccupancy(5, 4);
        BitBoard sut = new BitBoard();
        sut.setOccupancy(2, 4);
        sut.mirrorVertical();
        Assert.assertEquals(sut, expectedOut);
    }

    @Test
    public void test_toString() {
        final String expectedOut = "\n00000000" +
                "\n00000000" +
                "\n00000000" +
                "\n00000000" +
                "\n00000000" +
                "\n00010000" +
                "\n00000000" +
                "\n00000000\n";
        final BitBoard sut = new BitBoard();
        sut.setOccupancy(2, 4);
        Assert.assertThat("toString() does not produce the correct output", sut.toString(), is(expectedOut));
    }

    @Test
    public void setOccupancy() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(2, 4);
        Assert.assertEquals(Long.parseLong("8796093022208"), sut.getState());
    }


    @Test
    public void equals_self() {
        BitBoard sut = new BitBoard();
        Assert.assertEquals(sut,sut);
    }

    @Test
    public void equals_another_class() {
        BitBoard sut = new BitBoard();
        Assert.assertFalse("Comparision to another class does not return false",sut.equals(1));
    }
    @Test
    public void equals_another_bitboard() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(2,4);
        BitBoard other = new BitBoard();
        other.setOccupancy(2,4);
        Assert.assertEquals(sut,other);
    }

    @Test
    public void test_or(){
        BitBoard a = new BitBoard();
        a.setOccupancy(2,4);
        a.setOccupancy(4,4);

        BitBoard b = new BitBoard();
        b.setOccupancy(6,4);
        b.setOccupancy(4,4);

        BitBoard sut = BitBoard.or(a,b);

        BitBoard expectedOutput = new BitBoard();
        expectedOutput.setOccupancy(2,4);
        expectedOutput.setOccupancy(6,4);
        expectedOutput.setOccupancy(4,4);
        Assert.assertEquals(expectedOutput,sut);
    }

    @Test
    public void test_and(){
        BitBoard a = new BitBoard();
        a.setOccupancy(2,4);
        a.setOccupancy(4,4);

        BitBoard b = new BitBoard();
        b.setOccupancy(6,4);
        b.setOccupancy(4,4);

        BitBoard sut = BitBoard.and(a,b);

        BitBoard expectedOutput = new BitBoard();
        expectedOutput.setOccupancy(4,4);
        Assert.assertEquals(expectedOutput,sut);
    }

    @Test
    public void test_xor(){
        BitBoard a = new BitBoard();
        a.setOccupancy(2,4);
        a.setOccupancy(4,4);

        BitBoard b = new BitBoard();
        b.setOccupancy(6,4);
        b.setOccupancy(4,4);

        BitBoard sut = BitBoard.xor(a,b);

        BitBoard expectedOutput = new BitBoard();
        expectedOutput.setOccupancy(2,4);
        expectedOutput.setOccupancy(6,4);
        Assert.assertEquals(expectedOutput,sut);
    }
}