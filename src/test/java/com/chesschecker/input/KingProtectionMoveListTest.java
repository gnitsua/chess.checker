package com.chesschecker.input;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class KingProtectionMoveListTest {

    @Test
    public void testCreationFromValidMoveList(){
        PieceList white = new PieceList("Rf1, Kg1, Pf2, Ph2, Pg3");
        PieceList whiteNoKing = new PieceList("Rf1, Pf2, Ph2, Pg3");
        KingProtectionMoveList sut = new KingProtectionMoveList(white);
        MoveList baseclass = new MoveList(whiteNoKing);
        Assert.assertEquals(sut,baseclass);
    }

    @Test
    public void testCreationFromNoKingList(){
        PieceList white = new PieceList("Rf1, Pf2, Ph2, Pg3");
        PieceList whiteNoKing = new PieceList("Rf1, Pf2, Ph2, Pg3");
        KingProtectionMoveList sut = new KingProtectionMoveList(white);
        MoveList baseclass = new MoveList(whiteNoKing);
        Assert.assertEquals(sut,baseclass);
    }

}