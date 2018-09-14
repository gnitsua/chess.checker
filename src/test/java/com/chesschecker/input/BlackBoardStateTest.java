package com.chesschecker.input;

import com.chesschecker.gamestate.BlackBoardState;
import com.chesschecker.gamestate.WhiteBoardState;
import com.chesschecker.moves.BoardMove;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ALL")
public class BlackBoardStateTest {
    @Test
    public void test_ForFilteringKing() {
        PieceList white = new PieceList("Rf1, Kg1, Pf2, Ph2, Pg3");
        PieceList whiteNoKing = new PieceList("Rf8, Pf7, Ph7, Pg6");
        PieceList black = new PieceList("Kb8, Ne8, Pa7, Pb7, Pc7, Ra5");
        PieceList blackNoKing = new PieceList("Ne1, Pa2, Pb2, Pc2, Ra4");
        BlackBoardState sut = new BlackBoardState(white, black);

        Assert.assertEquals("White is not correct", blackNoKing, sut.getWhiteWithoutKing());
        Assert.assertEquals("Black is not correct", whiteNoKing, sut.getBlackWithoutKing());
    }

    @Test
    public void test_ForNotHavingKing() {
        PieceList white = new PieceList("Rf1, Pf2, Ph2, Pg3");
        PieceList whiteNoKing = new PieceList("Rf8, Pf7, Ph7, Pg6");
        PieceList black = new PieceList("Ne8, Pa7, Pb7, Pc7, Ra5");
        PieceList blackNoKing = new PieceList("Ne1, Pa2, Pb2, Pc2, Ra4");
        BlackBoardState sut = new BlackBoardState(white, black);

        Assert.assertEquals("White is not correct", blackNoKing, sut.getWhiteWithoutKing());
        Assert.assertEquals("Black is not correct", whiteNoKing, sut.getBlackWithoutKing());
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

    @Test
    public void test_getOritented() {
        PieceList white = new PieceList("Rf1, Pf2, Ph2, Pg3");
        PieceList black = new PieceList("Ne8, Pa7, Pb7, Pc7, Ra5");
        PieceList whiteFlipped = new PieceList("Rf8, Pf7, Ph7, Pg6");
        PieceList blackFlipped= new PieceList("Ne1, Pa2, Pb2, Pc2, Ra4");
        BlackBoardState sut = new BlackBoardState(whiteFlipped, blackFlipped);
        WhiteBoardState sut2 = new WhiteBoardState(white, black);

        Assert.assertEquals("White is not correct", sut2.getWhite(), sut.getOrientedBlack());
        Assert.assertEquals("Black is not correct", sut2.getBlack(), sut.getOrientedWhite());

    }

    @Test
    public void test_getvalidmoves() {
        PieceList white = new PieceList("Pf2");
        PieceList black = new PieceList("Pf7");
        PieceList whiteMove = new PieceList("Pf2");
        PieceList blackMove = new PieceList("Pf7");
        BlackBoardState sut = new BlackBoardState(white, black, blackMove);
        WhiteBoardState sut2 = new WhiteBoardState(white, black, whiteMove);
        System.out.println(sut.getValidMoves());
        System.out.println(sut2.getValidMoves());
        Assert.assertEquals(sut2.getValidMoves(),sut.getValidMoves());
    }
}