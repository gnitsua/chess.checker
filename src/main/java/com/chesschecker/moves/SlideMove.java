package com.chesschecker.moves;

import com.chesschecker.input.Board;
import com.chesschecker.util.BitBoard;
import com.google.common.collect.Streams;

import java.util.stream.Stream;

/**
 * This class defines of a piece that slides.
 * Moves are not valid if moves over any intervening pieces.
 * This is defined in 3.5 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class SlideMove extends ColoredMove {
    SlideMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    /**
     * @param start int to start stepping from
     * @param stop  int to stop stepping at (inclusive)
     * @return A stream that moves from start to stop in integer steps where the steps are made
     * made diagonally first until either row or column is exhausted, then column wise or row wise
     * after this depending on which depleted first.
     */
    private static Stream<Integer> stepStream(final int start, final int stop) {
        if (0 < (start - stop)) {
            return Stream.iterate(start,
                    i -> {
                        final int max = Math.max(i - 1, stop);
                        return max;
                    });
        } else {
            return Stream.iterate(start,
                    i -> {
                        final int min = Math.min(i + 1, stop);
                        return min;
                    });
        }

    }

    private BitBoard getPassedThroughSquares() {
        final Stream<Integer> rowStepStream = SlideMove.stepStream(this.startRow, this.endRow);
        final Stream<Integer> colStepsStream = SlideMove.stepStream(this.startCol, this.endCol);
        final BitBoard passesThrough = new Board();
        final int rows = Math.abs(this.startRow - this.endRow);
        final int columns = Math.abs(this.startCol - this.endCol);
        final int numSteps = Math.max(rows, columns);
        final Stream<Integer> rowSteps = rowStepStream.limit((long) numSteps);
        final Stream<Integer> columnSteps = colStepsStream.limit((long) numSteps);
        Streams.forEachPair(rowSteps, columnSteps, passesThrough::setOccupancy);
        // We don't want to check the start or end columns (start would always be occupied,
        // and end is checked by ColorMove
        final BitBoard startAndEnd = new Board();
        startAndEnd.setOccupancy(this.startRow, this.startCol);
        startAndEnd.setOccupancy(this.endRow, this.endCol);
        final BitBoard invertedStartAndEnd = Board.not(startAndEnd);
        return Board.and(passesThrough, invertedStartAndEnd);
    }

    final boolean isValidSlideMove(final BitBoard friendly, final BitBoard foe) {
        final BitBoard passesThrough = this.getPassedThroughSquares();
        final BitBoard friendAndFoe = Board.or(friendly, foe);// Intervening pieces can be friend or foe
        final BitBoard temp = Board.and(passesThrough, friendAndFoe);
        if (temp.isEmpty()) {
            //there are no intervening pieces
            return true;
        } else {
            return false;
        }

    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (this.isValidBoardMove()) {
            if (this.isValidColoredMove(friendly)) {
                if (this.isValidSlideMove(friendly, foe)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
