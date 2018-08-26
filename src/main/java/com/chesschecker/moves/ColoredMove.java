package com.chesschecker.moves;

import com.chesschecker.input.Board;
import com.chesschecker.util.BitBoard;

/**
 * This class defines moves for a piece of a certain color.
 * Moves are not valid if they end on a piece of the same color,
 * or if the piece it lands on is itself (it doesn't actually move)
 * This is defined in 3.1 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class ColoredMove extends BoardMove {
    ColoredMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    final boolean isMoveToEmpty(final BitBoard friendly) {
        final BitBoard temp = new Board();
        temp.setOccupancy(this.endRow, this.endCol);
        final BitBoard overlapWithFriendly = Board.and(temp, friendly);
        if (BitBoard.isEmpty(overlapWithFriendly)) {
            return true;
        } else {
            return false;
        }
    }

    final boolean isValidColoredMove(final BitBoard friendly) {
        if (this.isMoveToEmpty(friendly)) {
            return true;
        } else {
            return false;
        }
    }

    final boolean isValidBishopMove() {
        if (Math.abs(this.startRow - this.endRow) == Math.abs(this.startCol - this.endCol)) {
            return true;
        } else {
            return false;
        }
    }

    final boolean isValidRookMove() {
        if (this.startRow == this.endRow) {
            return true;
        } else {
            if (this.startCol == this.endCol) {
                return true;
            } else {
                return false;
            }
        }
    }

    final boolean isValidQueenMove() {
        if (this.isValidBishopMove()) {
            return true;
        } else {
            if (this.isValidRookMove()) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if (this.isValidBoardMove()) {
            if (this.isValidColoredMove(friendly)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


}
