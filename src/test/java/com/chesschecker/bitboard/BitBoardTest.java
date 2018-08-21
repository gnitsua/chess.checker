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
    public void setOccupancy() {
        BitBoard sut = new BitBoard();
        sut.setOccupancy(2,4);

        Assert.assertEquals(Long.parseLong("8796093022208") , sut.getState());
    }

    @Test
    public void test_toString() {
        final String expectedOut = "\n00000000\n00000000\n00000000\n00000000\n00000000\n00010000\n00000000\n00000000\n";
        final BitBoard sut = new BitBoard();
        sut.setOccupancy(2,4);
        Assert.assertThat("toString() does not produce the correct output",sut.toString(), is(expectedOut));
    }
}