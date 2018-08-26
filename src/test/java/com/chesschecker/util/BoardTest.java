package com.chesschecker.util;

import com.chesschecker.input.Board;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

@SuppressWarnings("ALL")
public class BoardTest {

    @Test
    public void test_setOccupancy_invalid_1() {
        BitBoard sut = new Board();
        sut.setOccupancy(-1, 4);
        Assert.assertEquals(Long.parseLong("0"), sut.toLong());
    }

    @Test
    public void test_setOccupancy_invalid_2() {
        Board sut = new Board();
        sut.setOccupancy(9, 4);
        Assert.assertEquals(Long.parseLong("0"), sut.toLong());
    }

    @Test
    public void test_setOccupancy_invalid_3() {
        Board sut = new Board();
        sut.setOccupancy(4, -1);
        Assert.assertEquals(Long.parseLong("0"), sut.toLong());
    }

    @Test
    public void test_setOccupancy_invalid_4() {
        Board sut = new Board();
        sut.setOccupancy(4, 9);
        Assert.assertEquals(Long.parseLong("0"), sut.toLong());
    }

    @Test
    public void test_flipVertical() {
        final Board expectedOut = new Board();
        expectedOut.setOccupancy(5, 4);
        Board sut = new Board();
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
                "\n00001000" +
                "\n00000000" +
                "\n00000000\n";
        final Board sut = new Board();
        sut.setOccupancy(2, 4);
        Assert.assertThat("toString() does not produce the correct output", sut.toString(), is(expectedOut));
    }

    @Test
    public void test_toString_2() {
        final String expectedOut = "\n00000000" +
                "\n00000000" +
                "\n00000000" +
                "\n00000000" +
                "\n00000000" +
                "\n00000000" +
                "\n00000000" +
                "\n10000000\n";
        final Board sut = new Board();
        sut.setOccupancy(0, 0);
        Assert.assertThat("toString() does not produce the correct output", sut.toString(), is(expectedOut));
    }

    @Test
    public void setOccupancy() {
        Board sut = new Board();
        sut.setOccupancy(2, 4);
        Assert.assertEquals(Long.parseLong("8796093022208"), sut.toLong());
    }

    /**
     * Test to check behavior of the internal long at the edge case
     */
    @Test
    public void setOccupancy_2() {
        Board sut = new Board();
        sut.setOccupancy(0, 0);
        Assert.assertEquals(true, Long.parseUnsignedLong("9223372036854775808")==sut.toLong());
    }


    @Test
    public void equals_self() {
        Board sut = new Board();
        Assert.assertEquals(sut, sut);
    }

    @Test
    public void equals_another_class() {
        Board sut = new Board();
        Assert.assertFalse("Comparision to another class does not return false", sut.equals(1));
    }

    @Test
    public void equals_another_bitboard() {
        Board sut = new Board();
        sut.setOccupancy(2, 4);
        Board other = new Board();
        other.setOccupancy(2, 4);
        Assert.assertEquals(sut, other);
    }

    @Test
    public void test_or() {
        Board a = new Board();
        a.setOccupancy(2, 4);
        a.setOccupancy(4, 4);

        Board b = new Board();
        b.setOccupancy(6, 4);
        b.setOccupancy(4, 4);

        BitBoard sut = Board.or(a, b);

        Board expectedOutput = new Board();
        expectedOutput.setOccupancy(2, 4);
        expectedOutput.setOccupancy(6, 4);
        expectedOutput.setOccupancy(4, 4);
        Assert.assertEquals(expectedOutput, sut);
    }

    @Test
    public void test_and() {
        Board a = new Board();
        a.setOccupancy(2, 4);
        a.setOccupancy(4, 4);

        Board b = new Board();
        b.setOccupancy(6, 4);
        b.setOccupancy(4, 4);

        BitBoard sut = Board.and(a, b);

        Board expectedOutput = new Board();
        expectedOutput.setOccupancy(4, 4);
        Assert.assertEquals(expectedOutput, sut);
    }

    @Test
    public void test_xor() {
        Board a = new Board();
        a.setOccupancy(2, 4);
        a.setOccupancy(4, 4);

        Board b = new Board();
        b.setOccupancy(6, 4);
        b.setOccupancy(4, 4);

        BitBoard sut = Board.xor(a, b);

        Board expectedOutput = new Board();
        expectedOutput.setOccupancy(2, 4);
        expectedOutput.setOccupancy(6, 4);
        Assert.assertEquals(expectedOutput, sut);
    }

    @Test
    public void isNotEmpty() {
        Board sut = new Board();
        sut.setOccupancy(4,4);
        Assert.assertEquals(false, sut.isEmpty());
    }
    @Test
    public void isEmpty() {
        Board sut = new Board();
        Assert.assertEquals(true, sut.isEmpty());
    }
}