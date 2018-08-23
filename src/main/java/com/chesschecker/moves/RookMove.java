package com.chesschecker.moves;

import com.chesschecker.bitboard.BitBoard;


/**
 * This class defines of a piece a Rook.
 * The rook may move to any square along the file or the rank on which it stands.
 * This is defined in 3.3 of https://www.fide.com/fide/handbook.html?id=171&view=article
 */
public class RookMove extends SlideMove{
    protected static final String PIECE_ABBREVIATION = "R";


    public RookMove(int startrow, int startcol, int endrow, int endcol) {
        super(startrow, startcol, endrow, endcol);
    }

    @Override
    public boolean isValid(final BitBoard friendly, final BitBoard foe) {
        if(super.isValid(friendly, foe)){
            if (this.startRow == this.endRow){
                return true;
            }
            else{
                if(this.startCol == this.endCol){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        else{
            return false;
        }
    }

    public String toString(){
        return this.PIECE_ABBREVIATION + super.toString();
    }
}
