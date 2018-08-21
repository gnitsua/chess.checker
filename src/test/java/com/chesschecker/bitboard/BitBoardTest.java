package com.chesschecker.bitboard;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BitBoardTest {

    @Test
    public void test_setOccupancy_valid() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(2,4);
        Assert.assertEquals(Long.parseLong("8796093022208") , sut.getState());
    }

    @Test
    public void test_setOccupancy_invalid_1() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(-1,4);
        Assert.assertEquals(Long.parseLong("0") , sut.getState());
    }

    @Test
    public void test_setOccupancy_invalid_2() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(9,4);
        Assert.assertEquals(Long.parseLong("0") , sut.getState());
    }

    @Test
    public void test_setOccupancy_invalid_3() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(4,-1);
        Assert.assertEquals(Long.parseLong("0") , sut.getState());
    }

    @Test
    public void test_setOccupancy_invalid_4() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(4,9);
        Assert.assertEquals(Long.parseLong("0") , sut.getState());
    }

    @Test
    public void test_toString() {
        final String expectedOut = "\n00000000\n00000000\n00000000\n00000000\n00000000\n00010000\n00000000\n00000000\n";
        final BitBoard sut = new BitBoard();
        sut.setOccupancy(2,4);
        Assert.assertThat("toString() does not produce the correct output",sut.toString(), is(expectedOut));
    }
}