package com.chesschecker.input;

import com.chesschecker.moves.*;
import com.chesschecker.util.BitBoard;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("ALL")
public class MoveListTest {
    @Test
    public void testMultiplePositions() {
        //I tried to get fancy with mocking here, but I couldn't get it to work
        PieceList white = new PieceList("Ph2, Pg3");
        PieceList a = new PieceList("Ph2");
        MoveList expectedOuta = new MoveList(a);
        PieceList b = new PieceList("Pg3");
        MoveList expectedOutb = new MoveList(b);
        expectedOuta.addAll(expectedOutb);//Should be the concat of the individual moves
        Set<BoardMove> sut = new MoveList(white);

        Assert.assertEquals(true, sut.equals(expectedOuta));
    }


    @Test
    public void getSudoValidMovesKing() {
        final int startRow = 0;
        final int startCol = 5;
        Collection<BoardMove> sut = MoveList.getPseudoLegalMovesForPosition("Kf1");
        sut.forEach(boardMove -> {
            Assert.assertEquals(true,
                    (boardMove instanceof KingMove) ||
                            (boardMove instanceof CastlingKingMove)
            );
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesQueen() {
        final int startRow = 0;
        final int startCol = 5;
        Collection<BoardMove> sut = MoveList.getPseudoLegalMovesForPosition("Qf1");
        sut.forEach(boardMove -> {
            Assert.assertThat(boardMove, CoreMatchers.instanceOf(QueenMove.class));
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesRook() {
        final int startRow = 0;
        final int startCol = 5;
        Collection<BoardMove> sut = MoveList.getPseudoLegalMovesForPosition("Rf1");
        sut.forEach(boardMove -> {
            Assert.assertThat(boardMove, CoreMatchers.instanceOf(RookMove.class));
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesBishop() {
        final int startRow = 0;
        final int startCol = 5;
        Collection<BoardMove> sut = MoveList.getPseudoLegalMovesForPosition("Bf1");
        sut.forEach(boardMove -> {
            Assert.assertThat(boardMove, CoreMatchers.instanceOf(BishopMove.class));
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesKnight() {
        final int startRow = 0;
        final int startCol = 5;
        Collection<BoardMove> sut = MoveList.getPseudoLegalMovesForPosition("Nf1");
        sut.forEach(boardMove -> {
            Assert.assertThat(boardMove, CoreMatchers.instanceOf(KnightMove.class));
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesPawn() {
        final int startRow = 0;
        final int startCol = 5;
        Collection<BoardMove> sut = MoveList.getPseudoLegalMovesForPosition("Pf1");
        sut.forEach(boardMove -> {
            Assert.assertEquals(true,
                    (boardMove instanceof PawnMove) ||
                            (boardMove instanceof PawnCaptureMove)
            );
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesInvalidString() {
        final int startRow = 0;
        final int startCol = 5;
        Collection<BoardMove> sut = MoveList.getPseudoLegalMovesForPosition("f1");
        Assert.assertEquals(new HashSet<BoardMove>(), sut);
    }

    @Test
    public void getSudoValidMovesInvalidType() {
        final int startRow = 0;
        final int startCol = 5;
        Collection<BoardMove> sut = MoveList.getPseudoLegalMovesForPosition("Zf1");
        Assert.assertEquals(new HashSet<BoardMove>(), sut);
    }

    @Test
    public void getOccupancy() {
        PieceList white = new PieceList("Ph2, Pg3");
        MoveList sut = new MoveList(white);
        Board expectedOut = new Board();
        expectedOut.setOccupancy(1,7);
        expectedOut.setOccupancy(2,6);
        Assert.assertEquals(expectedOut,sut.getOccupancy());
    }

    @Test
    public void getAttacking() {
    }
}