package com.chesschecker.input;

import com.chesschecker.gamestate.BlackBoardState;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ALL")
public class BlackBoardStateTest {
    @Test
    public void test_ForFilteringKing() {
        PieceList white = new PieceList("Rf1, Kg1, Pf2, Ph2, Pg3");
        PieceList whiteNoKing = new PieceList("Rf1, Pf2, Ph2, Pg3");
        PieceList black = new PieceList("Kb8, Ne8, Pa7, Pb7, Pc7, Ra5");
        PieceList blackNoKing = new PieceList("Ne8, Pa7, Pb7, Pc7, Ra5");
        BlackBoardState sut = new BlackBoardState(white, black);

        Assert.assertEquals("White is not correct", whiteNoKing, sut.getWhiteWithoutKing());
        Assert.assertEquals("Black is not correct", blackNoKing, sut.getBlackWithoutKing());
    }

    @Test
    public void test_ForNotHavingKing() {
        PieceList white = new PieceList("Rf1, Pf2, Ph2, Pg3");
        PieceList whiteNoKing = new PieceList("Rf1, Pf2, Ph2, Pg3");
        PieceList black = new PieceList("Ne8, Pa7, Pb7, Pc7, Ra5");
        PieceList blackNoKing = new PieceList("Ne8, Pa7, Pb7, Pc7, Ra5");
        BlackBoardState sut = new BlackBoardState(white, black);

        Assert.assertEquals("White is not correct", whiteNoKing, sut.getWhiteWithoutKing());
        Assert.assertEquals("Black is not correct", blackNoKing, sut.getBlackWithoutKing());
    }

    @Test
    public void test_toString() {
        String testString = "[a, b, c]\n[a, b, c]\n";
        String set = "a, b, c";
        PieceList white = new PieceList(set);
        PieceList black = new PieceList(set);
        BlackBoardState sut = new BlackBoardState(white, black);

        Assert.assertEquals(testString, sut.toString());
    }
}