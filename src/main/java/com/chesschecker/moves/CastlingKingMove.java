package com.chesschecker.moves;

import com.chesschecker.util.BitBoard;

import java.util.Objects;

/**
 * This class defines the king's half of the castling move.
 * The king is transferred from its original square two squares towards the rook on its original square
 * This is defined in 3.8.b of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
@SuppressWarnings("EqualsAndHashcode")
public class CastlingKingMove extends QueenMove {
    private static final String PIECE_ABBREVIATION = "K";


    CastlingKingMove(final int startrow, final int startcol, final int endrow, final int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    private boolean isValidCastlingKingMove() {
        if (0 == this.startRow) {
            if (4 == this.startCol) {
                if (2 == this.endCol) {
                    return true;
                } else {
                    if (6 == this.endCol) {
                        return true;
                    } else {
                        return false;
                    }
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
        if(this.isValidBoardMove()){
            if(this.isValidColoredMove(friendly)){
                if(this.isValidSlideMove(friendly, foe)) {
                    if (this.isValidQueenMove()) {
                        if (this.isValidCastlingKingMove()) {
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
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return Objects.hash(CastlingKingMove.PIECE_ABBREVIATION, this.startRow, this.startCol, this.endRow, this.endCol);
    }

    @Override
    @SuppressWarnings("DesignForExtension")
    public String toString() {
        return CastlingKingMove.PIECE_ABBREVIATION + this.endPositionToString();
    }
}
