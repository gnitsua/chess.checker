package com.chesschecker.input;

import com.chesschecker.util.PieceAbbreviation;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class PieceListTest {

    @Test
    public void filterOutKing() {
        PieceList white = new PieceList("Kb1, Rf1, Pf2, Ph2, Pg3");
        PieceList expectedOut = new PieceList("Rf1, Pf2, Ph2, Pg3");
        Assert.assertEquals(PieceList.filterOutKing(white),expectedOut);
    }

    @Test
    public void flipRows() {
        PieceList white = new PieceList("Kb1, Rf1, Pf2, Ph2, Pg3");
        PieceList expectedOut = new PieceList("Kb8, Rf8, Pf7, Ph7, Pg6");
        Assert.assertEquals(PieceList.flipRows(white),expectedOut);
    }

    @Test
    public void testCopyConstructor() {
        PieceList white = new PieceList("Kb1, Rf1, Pf2, Ph2, Pg3");
        PieceList sut = new PieceList(white);
        Assert.assertEquals(white,sut);
    }
}