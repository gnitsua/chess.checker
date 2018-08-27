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
    protected final int startRow;
    protected final int startCol;
    final int endRow;
    final int endCol;


    BoardMove(final int startrow, final int startcol, final int endrow, final int endcol) {
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

    public static Move reverse(final Move move) {
        final int endRow = move.getEndRow();
        final int endCol = move.getEndCol();
        final int startRow = move.getStartRow();
        final int startCol = move.getStartCol();
        return new BoardMove(endRow, endCol, startRow, startCol);
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
        result.setOccupancy(this.endRow, this.endCol);
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

//    @Override
//    public final boolean equals(final Object obj) {
//        boolean returnVal = false;
//        if (obj == this) {
//            returnVal = true;
//        } else if (obj instanceof BoardMove) {//TODO: move this to Move?
//
//            // typecast o to Complex so that we can compare data members
//            final Move otherMove = (Move) obj;
//
//            // Compare the data members and return accordingly
//
//        }
//
//        return returnVal;
//    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.startRow, this.startCol, this.endRow, this.endCol);
    }

    @Override
    public final int compareTo(final Move o) {
        if(this.getStartRow() == o.getStartRow()){
            if(this.getStartCol() == o.getStartCol()) {
                if(this.getEndRow() == o.getEndRow()) {
                    if(this.getEndCol() == o.getEndCol()) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof BoardMove)) {
            return false;
        } else {
            return 0 == this.compareTo((Move) obj);
        }
    }
}
