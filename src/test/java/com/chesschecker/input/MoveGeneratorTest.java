package com.chesschecker.input;

import com.chesschecker.moves.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.instanceOf;

@SuppressWarnings("ALL")
public class MoveGeneratorTest {

    @Test
    public void getSudoValidMovesKing() {
        final int startRow = 1;
        final int startCol = 5;
        HashSet<BoardMove> sut = MoveGenerator.getSudoValidMoves("Kf1");
        sut.forEach(boardMove -> {
            Assert.assertThat(boardMove, instanceOf(KingMove.class));
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesQueen() {
        final int startRow = 1;
        final int startCol = 5;
        HashSet<BoardMove> sut = MoveGenerator.getSudoValidMoves("Qf1");
        sut.forEach(boardMove -> {
            Assert.assertThat(boardMove, instanceOf(QueenMove.class));
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesRook() {
        final int startRow = 1;
        final int startCol = 5;
        HashSet<BoardMove> sut = MoveGenerator.getSudoValidMoves("Rf1");
        sut.forEach(boardMove -> {
            Assert.assertThat(boardMove, instanceOf(RookMove.class));
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesBishop() {
        final int startRow = 1;
        final int startCol = 5;
        HashSet<BoardMove> sut = MoveGenerator.getSudoValidMoves("Bf1");
        sut.forEach(boardMove -> {
            Assert.assertThat(boardMove, instanceOf(BishopMove.class));
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesKnight() {
        final int startRow = 1;
        final int startCol = 5;
        HashSet<BoardMove> sut = MoveGenerator.getSudoValidMoves("Nf1");
        sut.forEach(boardMove -> {
            Assert.assertThat(boardMove, instanceOf(KnightMove.class));
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesPawn() {
        final int startRow = 1;
        final int startCol = 5;
        HashSet<BoardMove> sut = MoveGenerator.getSudoValidMoves("Pf1");
        sut.forEach(boardMove -> {
            Assert.assertEquals(true,
                    (boardMove instanceof PawnMove) ||
                            (boardMove instanceof PawnCaptureMove) ||
                            (boardMove instanceof Pawn37DMove)
            );
            Assert.assertEquals(startRow, boardMove.getStartRow());
            Assert.assertEquals(startCol, boardMove.getStartCol());
        });
    }

    @Test
    public void getSudoValidMovesInvalidString() {
        final int startRow = 1;
        final int startCol = 5;
        HashSet<BoardMove> sut = MoveGenerator.getSudoValidMoves("f1");
        Assert.assertEquals(new HashSet<BoardMove>(), sut);
    }

    @Test
    public void getSudoValidMovesInvalidType() {
        final int startRow = 1;
        final int startCol = 5;
        HashSet<BoardMove> sut = MoveGenerator.getSudoValidMoves("Zf1");
        Assert.assertEquals(new HashSet<BoardMove>(), sut);
    }
}