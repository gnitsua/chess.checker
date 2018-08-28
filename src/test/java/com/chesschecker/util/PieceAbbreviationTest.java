package com.chesschecker.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.NotSerializableException;

@SuppressWarnings("ALL")
public class PieceAbbreviationTest {

    @Test
    public void getAbbreviation() {
        Assert.assertEquals("K", PieceAbbreviation.KING.getAbbreviation());
        Assert.assertEquals("Q", PieceAbbreviation.QUEEN.getAbbreviation());
        Assert.assertEquals("R", PieceAbbreviation.ROOK.getAbbreviation());
        Assert.assertEquals("N", PieceAbbreviation.KNIGHT.getAbbreviation());
        Assert.assertEquals("B", PieceAbbreviation.BISHOP.getAbbreviation());
        Assert.assertEquals("P", PieceAbbreviation.PAWN.getAbbreviation());

    }


    public void getAbbreviationFromString() {
        Assert.assertEquals(PieceAbbreviation.KING,PieceAbbreviation.fromAbbreviation("K"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBadAbbreviation() {
        PieceAbbreviation.fromAbbreviation("Z");
    }
}