package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;
import com.google.common.collect.Streams;

import java.util.stream.Stream;

/**
 * This class defines of a piece that slides.
 * Moves are not valid if moves over any intervening pieces.
 * This is defined in 3.5 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class SlideMove extends ColoredMove {
    public SlideMove(int startrow, int startcol, int endrow, int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    /**
     * @param start
     * @param stop
     * @return A stream that moves from start to stop in integer steps where the steps are made
     * made diagonally first until either row or column is exhausted, then column wise or row wise
     * after this depending on which depleted first.
     */
    private Stream<Integer> stepStream(final int start, final int stop) {
        final int direction;
        if (0 < (start - stop)) {
            return Stream.iterate(Integer.valueOf(start),
                    i -> Integer.valueOf(Math.max(i.intValue() - 1, stop)));
        } else {
            return Stream.iterate(Integer.valueOf(start),
                    i -> Integer.valueOf(Math.min(i.intValue() + 1, stop)));
        }

    }

    public BitBoard getPassedThroughSquares() {
        final Stream<Integer> rowSteps = this.stepStream(this.startRow, this.endRow);
        final Stream<Integer> colSteps = this.stepStream(this.startCol, this.endCol);
        final BitBoard passesThrough = new BitBoard();
        final int numSteps = Math.max(Math.abs(this.startRow - this.endRow), Math.abs(this.startCol - this.endCol));
        Streams.forEachPair(rowSteps.limit((long) numSteps), colSteps.limit((long) numSteps), passesThrough::setOccupancy);
        // We don't want to check the start or end columns (start would always be occupied,
        // and end is checked by ColorMove
        final BitBoard startAndEnd = new BitBoard();
        startAndEnd.setOccupancy(this.startRow, this.startCol);
        final BitBoard invertedStartAndEnd = BitBoard.not(startAndEnd);
        return BitBoard.and(passesThrough, invertedStartAndEnd);
    }

    @Override
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (super.isValid(friendly, foe)) {
            final BitBoard passesThrough = this.getPassedThroughSquares();

            final BitBoard friendAndFoe = BitBoard.or(friendly, foe);// Intervening pieces can be friend or foe
            BitBoard temp = BitBoard.and(passesThrough, friendAndFoe);
            if (BitBoard.and(passesThrough, friendAndFoe).isEmpty()) {
                //there are no intervening pieces
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
