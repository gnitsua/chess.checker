package com.chesschecker.input;

import com.chesschecker.moves.BoardMove;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class KingProtectionMoveListTest {

    @Test
    public void testCreationFromValidMoveList(){
        PieceList white = new PieceList("Rf1, Kg1, Pf2, Ph2, Pg3");
        PieceList whiteNoKing = new PieceList("Kg1, Qg1, Rg1, Bg1, Ng1, Pg1");
        KingProtectionMoveList sut = new KingProtectionMoveList(white);
        MoveList baseclass = new MoveList(whiteNoKing);
        Assert.assertEquals(sut,baseclass);
    }

    @Test
    public void testCreationFromNoKingList(){
        PieceList white = new PieceList("Rf1, Pf2, Ph2, Pg3");
        KingProtectionMoveList sut = new KingProtectionMoveList(white);
        Assert.assertEquals(new HashSet< BoardMove >(),sut);
    }

    @Test
    public void generateImposters(){
        PieceList white = new PieceList("Kg1, Qg1, Rg1, Bg1, Ng1, Pg1");
        Collection<String> sut = KingProtectionMoveList.generateImpostors("Kg1");
        Assert.assertEquals(white,sut);

    }

}