package com.chesschecker.moves;

import com.chesschecker.input.Board;
import com.chesschecker.util.BitBoard;
import com.chesschecker.util.Column;

import java.util.Objects;

/**
 * A board move is defined as a move that starts and ends on a valid square of a 8x8 chess board
 * This comes from rule 2.1 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
@SuppressWarnings("FieldNotUsedInToString")
public class BoardMove implements Move {
    final int startRow;
    final int startCol;
    final int endRow;
    final int endCol;


    public BoardMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super();
        this.startRow = startrow;
        this.startCol = startcol;
        this.endRow = endrow;
        this.endCol = endcol;
    }

    private static boolean isValidRow(final int row) {
        if (0 <= row) {
            if (8 > row) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean isValidCol(final int row) {
        if (0 <= row) {
            if (8 > row) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isSelfMove() {
        if (this.startRow == this.endRow) {
            if (this.startCol == this.endCol) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    final boolean isValidBoardMove() {
        if (BoardMove.isValidRow(this.startRow)) {
            if (BoardMove.isValidRow(this.endRow)) {
                if (BoardMove.isValidCol(this.startCol)) {
                    if (BoardMove.isValidCol(this.endCol)) {
                        if (this.isSelfMove()) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
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

    @Override
    @SuppressWarnings("DesignForExtension")
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        return this.isValidBoardMove();
    }

    /**
     * @return BitBoard with the final position of the move set to occupied
     */
    public final BitBoard getAttacking() {
        final BitBoard result = new Board();
        result.setOccupancy(this.endRow, this.endCol);
        return result;
    }

    public final BitBoard getOccupancy() {
        final BitBoard result = new Board();
        result.setOccupancy(this.startRow, this.startCol);
        return result;
    }

    public final int getStartRow() {
        return this.startRow;
    }

    public final int getStartCol() {
        return this.startCol;
    }

    public final int getEndRow() {
        return this.endRow;
    }

    public final int getEndCol() {
        return this.endCol;
    }

    final String endPositionToString() {
        final StringBuilder result = new StringBuilder(0);
        final String str = Column.columnNumberToLetter(this.endCol);
        result.append(str);
        result.append(this.endRow + 1);
        return result.toString();
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public String toString() {
        return this.endPositionToString();
    }

    public final String toBoringString() { return this.endPositionToString(); }

    @Override
    @SuppressWarnings("DesignForExtension")
    public int hashCode() {
        return Objects.hash(this.startRow, this.startCol, this.endRow, this.endCol);
    }

    @Override
    public final int compareTo(final Move o) {
        if(this.hashCode() == o.hashCode()){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof BoardMove) {
            return 1 == this.compareTo((Move) obj);
        } else {
            return false;
        }
    }
}
