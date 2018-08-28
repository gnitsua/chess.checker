package com.chesschecker.input;

import com.chesschecker.gamestate.WhiteBoardState;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

@SuppressWarnings("ALL")
public class WhiteBoardStateTest {
    @Test
    public void test_EmptyConstructor() {
        HashSet<String> empty = new HashSet<>();
        WhiteBoardState sut = new WhiteBoardState();
        Assert.assertEquals("White not empty", empty, sut.getWhite());
        Assert.assertEquals("Black not empty", empty, sut.getBlack());
    }

    @Test
    public void test_ForFilteringKing() {
        PieceList white = new PieceList("Rf1, Kg1, Pf2, Ph2, Pg3");
        PieceList whiteNoKing = new PieceList("Rf1, Pf2, Ph2, Pg3");
        PieceList black = new PieceList("Kb8, Ne8, Pa7, Pb7, Pc7, Ra5");
        PieceList blackNoKing = new PieceList("Ne8, Pa7, Pb7, Pc7, Ra5");
        WhiteBoardState sut = new WhiteBoardState(white, black);

        Assert.assertEquals("White is not correct", whiteNoKing, sut.getWhiteWithoutKing());
        Assert.assertEquals("Black is not correct", blackNoKing, sut.getBlackWithoutKing());
    }

    @Test
    public void test_ForNotHavingKing() {
        PieceList white = new PieceList("Rf1, Pf2, Ph2, Pg3");
        PieceList whiteNoKing = new PieceList("Rf1, Pf2, Ph2, Pg3");
        PieceList black = new PieceList("Ne8, Pa7, Pb7, Pc7, Ra5");
        PieceList blackNoKing = new PieceList("Ne8, Pa7, Pb7, Pc7, Ra5");
        WhiteBoardState sut = new WhiteBoardState(white, black);

        Assert.assertEquals("White is not correct", whiteNoKing, sut.getWhiteWithoutKing());
        Assert.assertEquals("Black is not correct", blackNoKing, sut.getBlackWithoutKing());
    }

    @Test
    public void test_ForGettingKing() {
        PieceList white = new PieceList("Rf1, Kg1, Pf2, Ph2, Pg3");
        PieceList whiteKing = new PieceList("Kg1");
        PieceList black = new PieceList("Kb8, Ne8, Pa7, Pb7, Pc7, Ra5");
        PieceList blackKing = new PieceList("Kb8");
        WhiteBoardState sut = new WhiteBoardState(white, black);

        Assert.assertEquals("White is not correct", whiteKing, sut.getWhiteKing());
        Assert.assertEquals("Black is not correct", blackKing, sut.getBlackKing());
    }

    @Test
    public void test_toString() {
        String testString = "[a, b, c]\n[a, b, c]\n";
        String set = "a, b, c";
        PieceList white = new PieceList(set);
        PieceList black = new PieceList(set);
        WhiteBoardState sut = new WhiteBoardState(white, black);

        Assert.assertEquals(testString, sut.toString());
    }
}